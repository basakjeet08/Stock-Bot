package dev.anirban.stockbot.controller;

import dev.anirban.stockbot.constant.UrlConstants;
import dev.anirban.stockbot.dto.common.EmployeeDto;
import dev.anirban.stockbot.dto.request.CreateEmployeeRequest;
import dev.anirban.stockbot.dto.response.ResponseWrapper;
import dev.anirban.stockbot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping(UrlConstants.CREATE_EMPLOYEE_MANAGER)
    public ResponseWrapper<EmployeeDto> createManager(@RequestBody CreateEmployeeRequest employeeRequest) {
        EmployeeDto data = service.createManager(employeeRequest).toEmployeeDto();
        return new ResponseWrapper<>(data);
    }

    @PostMapping(UrlConstants.CREATE_EMPLOYEE_CASHIER)
    public ResponseWrapper<EmployeeDto> createCashier(@RequestBody CreateEmployeeRequest employeeRequest) {
        EmployeeDto data = service.createCashier(employeeRequest).toEmployeeDto();
        return new ResponseWrapper<>(data);
    }

    @PostMapping(UrlConstants.CREATE_EMPLOYEE_STAFF)
    public ResponseWrapper<EmployeeDto> createStaff(@RequestBody CreateEmployeeRequest employeeRequest) {
        EmployeeDto data = service.createStaff(employeeRequest).toEmployeeDto();
        return new ResponseWrapper<>(data);
    }
}