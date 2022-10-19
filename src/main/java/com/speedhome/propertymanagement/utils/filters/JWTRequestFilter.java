package com.speedhome.propertymanagement.utils.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedhome.propertymanagement.dtos.ErrorResponseDto;
import com.speedhome.propertymanagement.services.impl.CustomUserDetailService;
import com.speedhome.propertymanagement.utils.JWTTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 12:09 PM
 */
@Component
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {
    private final CustomUserDetailService customUserDetailService;

    private final JWTTokenUtil jwtTokenUtil;

    private final ObjectMapper objectMapper;
    private static final String[] ALLOWED_URIS = {
            "/v2",
            "/swagger-resources",
            "/swagger-resources",
            "/configuration",
            "/configuration",
            "/swagger-ui.html",
            "/webjars",
            "/v3",
            "/swagger-ui",
            "/authenticate",
            "/h2-console"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (Arrays.stream(ALLOWED_URIS).anyMatch(allowedUri -> request.getRequestURI().contains(allowedUri))) {
            chain.doFilter(request, response);
            return;
        }

        final String tokenHeader = request.getHeader("Authorization");

        String username = "";
        String jwtToken = "";
        String jwtTokenStartKeyword = "Bearer ";
        if (tokenHeader != null && tokenHeader.startsWith(jwtTokenStartKeyword)) {
            jwtToken = tokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage(), e);
                sendErrorResponse(response, "Unable to get JWT Token");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sendErrorResponse(response, "Invalid or Expired JWT Token Provided");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            sendErrorResponse(response, "JWT Token does not begin with Bearer String");
        }

        if (!StringUtils.isEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * In case of exception an error response will be sent back
     *
     * @param response response object which will send the error message back
     * @param message  The error message which will be sent back
     * @throws IOException : {@link IOException}
     */
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), new ErrorResponseDto(HttpStatus.UNAUTHORIZED.toString(), message));
    }

}
