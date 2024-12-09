package dev.anirban.stockbot.controller;

import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.util.UrlConstants;
import dev.anirban.stockbot.dto.common.EmployeeDto;
import dev.anirban.stockbot.dto.request.CreateEmployeeRequest;
import dev.anirban.stockbot.dto.response.ResponseWrapper;
import dev.anirban.stockbot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping(UrlConstants.FIND_EMPLOYEE_BY_ID)
    public ResponseWrapper<EmployeeDto> findById(@PathVariable Integer id) {
        EmployeeDto data = service.findById(id).toEmployeeDto();
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_EMPLOYEE_QUERY)
    public ResponseWrapper<List<EmployeeDto>> findEmployeeQuery(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "roles", required = false) Employee.EmployeeRole roles
    ) {
        List<Employee> employeeList;

        if (name != null && roles != null)
            employeeList = service.findByRolesAndNameContaining(roles, name);
        else if (name != null)
            employeeList = service.findByNameContaining(name);
        else if (roles != null)
            employeeList = service.findByRoles(roles);
        else
            employeeList = service.findAll();

        List<EmployeeDto> data = employeeList
                .stream()
                .map(Employee::toEmployeeDto)
                .toList();

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

    @DeleteMapping(UrlConstants.DELETE_EMPLOYEE)
    public ResponseWrapper<Void> deleteById(
            @AuthenticationPrincipal Employee employee,
            @PathVariable Integer id
    ) {
        service.deleteById(employee, id);
        return new ResponseWrapper<>("Employee with ID : " + id + " is deleted Successfully !!");
    }
}