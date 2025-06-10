package com.telefonica.service;

import com.telefonica.dto.LoginRequest;
import com.telefonica.dto.LoginResponse;
import com.telefonica.dto.UserInfo;
import com.telefonica.entity.LoginLog;
import com.telefonica.feign.DummyClient;
import com.telefonica.repository.LoginLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final DummyClient dummyClient;
    private final LoginLogRepository loginLogRepository;

    public UserInfo loginAndLog(LoginRequest loginRequest) {
        // Paso 1: login
        ResponseEntity<LoginResponse> loginResponse = dummyClient.login(loginRequest);

        if (!loginResponse.getStatusCode().is2xxSuccessful() || loginResponse.getBody() == null) {
            throw new RuntimeException("Login inválido");
        }

        LoginResponse response = loginResponse.getBody();
        String accessToken = response.accessToken();

        // Paso 2: get authenticated user info
        String cookie = "Authorization=" + accessToken;
        ResponseEntity<UserInfo> meResponse = dummyClient.getMe(cookie);

        if (!meResponse.getStatusCode().is2xxSuccessful() || meResponse.getBody() == null) {
            throw new RuntimeException("No se pudo obtener información del usuario");
        }

        UserInfo userInfo = meResponse.getBody();

        // Paso 3: guardar en base de datos
        LoginLog log = new LoginLog();
        log.setUsername(userInfo.username());
        log.setLoginTime(LocalDateTime.now());
        log.setAccessToken(response.accessToken());
        log.setRefreshToken(response.refreshToken());

        loginLogRepository.save(log);

        return userInfo;
    }
}