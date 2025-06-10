package com.telefonica.controller;

import com.telefonica.dto.LoginRequest;
import com.telefonica.dto.UserInfo;
import com.telefonica.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserInfo> login(@RequestBody LoginRequest request) {
        UserInfo userInfo = authService.loginAndLog(request);
        return ResponseEntity.ok(userInfo);
    }
}