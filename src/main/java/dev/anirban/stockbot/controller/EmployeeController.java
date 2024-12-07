package dev.anirban.stockbot.controller;

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

    @PostMapping("/employees")
    public ResponseWrapper<EmployeeDto> create(@RequestBody CreateEmployeeRequest employeeRequest) {
        EmployeeDto data = service.create(employeeRequest).toEmployeeDto();
        return new ResponseWrapper<>(data);
    }
}