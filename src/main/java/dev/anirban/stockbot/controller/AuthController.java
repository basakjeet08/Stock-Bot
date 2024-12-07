package dev.anirban.stockbot.controller;

import dev.anirban.stockbot.util.UrlConstants;
import dev.anirban.stockbot.dto.request.CreateEmployeeRequest;
import dev.anirban.stockbot.dto.response.ResponseWrapper;
import dev.anirban.stockbot.dto.response.TokenWrapper;
import dev.anirban.stockbot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping(UrlConstants.LOGIN_EMPLOYEE)
    public ResponseWrapper<TokenWrapper> login(@RequestBody CreateEmployeeRequest employeeRequest) {
        TokenWrapper data = service.login(employeeRequest);
        return new ResponseWrapper<>(data);
    }
}