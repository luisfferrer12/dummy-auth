package com.telefonica.service;

import com.telefonica.dto.LoginRequest;
import com.telefonica.dto.LoginResponse;
import com.telefonica.dto.UserInfo;
import com.telefonica.entity.LoginLog;
import com.telefonica.feign.DummyClient;
import com.telefonica.repository.LoginLogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @MockBean
    private DummyClient dummyClient;

    @MockBean
    private LoginLogRepository loginLogRepository;

    @Test
    void testLoginAndLog_failed(){
        LoginRequest badRequest = new LoginRequest("felipe", "felipePass");

        when(dummyClient.login(badRequest))
                .thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(null));

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class, ()-> authService.loginAndLog(badRequest));

        assertThat(exception.getMessage()).isEqualTo("Login inválido");

        verify(loginLogRepository, never()).save(any(LoginLog.class));
    }
}
