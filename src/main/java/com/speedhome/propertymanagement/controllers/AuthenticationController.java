package com.speedhome.propertymanagement.controllers;

import com.speedhome.propertymanagement.dtos.AuthResponse;
import com.speedhome.propertymanagement.dtos.CustomUserDetails;
import com.speedhome.propertymanagement.dtos.UserDto;
import com.speedhome.propertymanagement.services.impl.CustomUserDetailService;
import com.speedhome.propertymanagement.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 12:20 PM
 */
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody @Validated UserDto user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        CustomUserDetails userDetails = customUserDetailService.loadUserByUsername(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(userDetails.getUserId(),jwtTokenUtil.generateToken(userDetails)));
    }

}
