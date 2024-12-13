package com.example.buoi1.controller;

import com.example.buoi1.model.AuthRequest;
import com.example.buoi1.sercurity.JwtService;
import com.example.buoi1.sercurity.UserDetailsServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceIml userDetailsService;

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            // Xác thực người dùng
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                // Tạo JWT nếu người dùng hợp lệ
                return jwtService.generateToken(authRequest.getUsername());
            } else {
                throw new BadCredentialsException("Invalid user request!");
            }
        } catch (Exception e) {
            throw new Exception("Authentication failed: " + e.getMessage());
        }
    }
}
