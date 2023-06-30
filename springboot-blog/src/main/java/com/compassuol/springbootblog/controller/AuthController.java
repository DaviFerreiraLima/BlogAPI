package com.compassuol.springbootblog.controller;

import com.compassuol.springbootblog.payload.JWTAuthResponseDto;
import com.compassuol.springbootblog.payload.LoginDto;
import com.compassuol.springbootblog.payload.RegisterDto;
import com.compassuol.springbootblog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JWTAuthResponseDto> login(@RequestBody LoginDto loginDto){
        var token = authService.login(loginDto);
        var authResponse = new JWTAuthResponseDto();
        authResponse.setAccessToken(token);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping(value = {"/register","/signup"})
        public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(authService.register(registerDto),HttpStatus.CREATED);
    }

}
