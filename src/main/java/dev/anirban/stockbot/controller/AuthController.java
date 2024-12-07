package dev.anirban.stockbot.controller;

import dev.anirban.stockbot.constant.UrlConstants;
import dev.anirban.stockbot.dto.common.EmployeeDto;
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

    @PostMapping(UrlConstants.REGISTER_EMPLOYEE)
    public ResponseWrapper<EmployeeDto> create(@RequestBody CreateEmployeeRequest employeeRequest) {
        EmployeeDto data = service.register(employeeRequest).toEmployeeDto();
        return new ResponseWrapper<>(data);
    }

    @PostMapping(UrlConstants.LOGIN_EMPLOYEE)
    public ResponseWrapper<TokenWrapper> login(@RequestBody CreateEmployeeRequest employeeRequest) {
        TokenWrapper data = service.login(employeeRequest);
        return new ResponseWrapper<>(data);
    }
}