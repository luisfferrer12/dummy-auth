package com.telefonica.controller;

import com.telefonica.dto.LoginRequest;
import com.telefonica.dto.UserInfo;
import com.telefonica.dto.UserList;
import com.telefonica.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<UserInfo> login(@RequestBody LoginRequest request) {
        UserInfo userInfo = authService.loginAndLog(request);
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/users")
    public ResponseEntity<UserList> getUsers() {
        UserList userList = authService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
}