package dev.anirban.stockbot.controller;

import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.util.UrlConstants;
import dev.anirban.stockbot.dto.common.EmployeeDto;
import dev.anirban.stockbot.dto.request.CreateEmployeeRequest;
import dev.anirban.stockbot.dto.response.ResponseWrapper;
import dev.anirban.stockbot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping(UrlConstants.CREATE_EMPLOYEE)
    public ResponseWrapper<EmployeeDto> create(
            @AuthenticationPrincipal Employee employee,
            @RequestBody CreateEmployeeRequest employeeRequest
    ) {
        EmployeeDto data = service.create(employee, employeeRequest).toEmployeeDto();
        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.UPDATE_EMPLOYEE)
    public ResponseWrapper<EmployeeDto> update(
            @AuthenticationPrincipal Employee employee,
            @RequestBody CreateEmployeeRequest ownerRequest
    ) {

        EmployeeDto data = service.update(employee, ownerRequest).toEmployeeDto();
        return new ResponseWrapper<>(data);
    }
}