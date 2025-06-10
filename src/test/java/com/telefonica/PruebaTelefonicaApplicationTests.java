package com.telefonica;

import com.telefonica.dto.LoginRequest;
import com.telefonica.dto.LoginResponse;
import com.telefonica.dto.UserInfo;
import com.telefonica.feign.DummyClient;
import com.telefonica.repository.LoginLogRepository;
import com.telefonica.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class PruebaTelefonicaApplicationTests {

	@MockBean
	private DummyClient dummyClient;

	@MockBean
	private LoginLogRepository loginLogRepository;

	@Autowired
	private AuthService authService;

	@Test
	void shouldLoginAndSaveLog() {
		LoginRequest request = new LoginRequest("emilys", "emilyspass");
		LoginResponse loginResponse = new LoginResponse(1, "emilys", "emily@demo.com", "token123", "refresh123");
		UserInfo userInfo = new UserInfo(1, "emilys", "emily@demo.com");

		Mockito.when(dummyClient.login(any())).thenReturn(ResponseEntity.ok(loginResponse));
		Mockito.when(dummyClient.getMe(any())).thenReturn(ResponseEntity.ok(userInfo));

		UserInfo result = authService.loginAndLog(request);

		assertEquals("emilys", result.username());
	}
}

