package com.speedhome.propertymanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.speedhome.propertymanagement.dtos.AuthResponse;
import com.speedhome.propertymanagement.dtos.CustomUserDetails;
import com.speedhome.propertymanagement.dtos.UserDto;
import com.speedhome.propertymanagement.services.impl.CustomUserDetailService;
import com.speedhome.propertymanagement.utils.JWTTokenUtil;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 12:20 PM
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailService customUserDetailService;

    private final JWTTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody @Validated UserDto user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        CustomUserDetails userDetails = customUserDetailService.loadUserByUsername(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(userDetails.getUserId(),jwtTokenUtil.generateToken(userDetails)));
    }

}
