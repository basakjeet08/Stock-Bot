package dev.anirban.stockbot.service;

import dev.anirban.stockbot.dto.request.CreateEmployeeRequest;
import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.exception.EmployeeAlreadyExists;
import dev.anirban.stockbot.exception.EmployeeNotFound;
import dev.anirban.stockbot.exception.PermissionDenied;
import dev.anirban.stockbot.repo.EmployeeRepo;
import dev.anirban.stockbot.util.HierarchyValidator;
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

    // Only Owner will be able to call this
    public Employee create(Employee requester, CreateEmployeeRequest employeeRequest) {

        // Checking if the record is present
        if (employeeRepo.findByUsername(employeeRequest.getUsername()).isPresent())
            throw new EmployeeAlreadyExists(employeeRequest.getUsername());

        if (!HierarchyValidator.isHierarchyValid(requester.getRoles(), employeeRequest.getRoles()))
            throw new PermissionDenied();

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

    public Employee findByUsername(String username) {
        return employeeRepo
                .findByUsername(username)
                .orElseThrow(() -> new EmployeeNotFound(username));
    }

    public Employee update(String username, CreateEmployeeRequest ownerRequest) {

        // Fetching saved Record
        Employee savedOwner = employeeRepo
                .findByUsername(username)
                .orElseThrow(() -> new EmployeeNotFound(username));

        // Username update
        if (ownerRequest.getUsername() != null && !ownerRequest.getUsername().equals(username)) {
            if (employeeRepo.findByUsername(ownerRequest.getUsername()).isPresent())
                throw new EmployeeAlreadyExists(ownerRequest.getUsername());

            savedOwner.setUsername(ownerRequest.getUsername());
        }

        // Password Update
        if (ownerRequest.getPassword() != null)
            savedOwner.setPassword(encoder.encode(ownerRequest.getPassword()));

        // Address Update
        if (ownerRequest.getAddress() != null)
            savedOwner.setAddress(ownerRequest.getAddress());

        // Updated at update
        savedOwner.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return employeeRepo.save(savedOwner);
    }
}