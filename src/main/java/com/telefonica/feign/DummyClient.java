package com.telefonica.feign;

import com.telefonica.dto.LoginRequest;
import com.telefonica.dto.LoginResponse;
import com.telefonica.dto.UserInfo;
import com.telefonica.dto.UserList;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "dummyClient", url = "https://dummyjson.com")
public interface DummyClient {

    @PostMapping(value = "/auth/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);

    @GetMapping(value = "/auth/me")
    ResponseEntity<UserInfo> getMe(@RequestHeader("Cookie") String accessToken);

    @GetMapping(value = "/users")
    ResponseEntity<UserList> getUsers();
}