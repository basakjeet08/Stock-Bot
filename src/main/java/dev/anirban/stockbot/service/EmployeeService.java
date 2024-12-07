package dev.anirban.stockbot.service;

import dev.anirban.stockbot.dto.request.CreateEmployeeRequest;
import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.exception.EmployeeAlreadyExists;
import dev.anirban.stockbot.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder encoder;

    public Employee create(CreateEmployeeRequest employeeRequest) {

        // Checking if the record is present
        if (employeeRepo.findByUsername(employeeRequest.getUsername()).isPresent())
            throw new EmployeeAlreadyExists(employeeRequest.getUsername());

        // Creating new Entry
        Employee newEmployee = Employee
                .builder()
                .name(employeeRequest.getName())
                .username(employeeRequest.getUsername())
                .password(encoder.encode(employeeRequest.getPassword()))
                .address(employeeRequest.getAddress())
                .salary(employeeRequest.getSalary())
                .joiningDate(employeeRequest.getJoiningDate())
                .roles(employeeRequest.getRoles())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return employeeRepo.save(newEmployee);
    }
}