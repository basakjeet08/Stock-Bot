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
import java.util.HashSet;
import java.util.List;


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

        if (HierarchyValidator.isHierarchyInvalid(requester.getRoles(), employeeRequest.getRoles()))
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
                .restockList(new HashSet<>())
                .build();

        return employeeRepo.save(newEmployee);
    }

    public Employee findById(Integer id) {
        return employeeRepo
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFound(id));
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Employee findByUsername(String username) {
        return employeeRepo
                .findByUsername(username)
                .orElseThrow(() -> new EmployeeNotFound(username));
    }

    public List<Employee> findByNameContaining(String name) {
        return employeeRepo.findByNameContaining(name);
    }

    public List<Employee> findByRoles(Employee.EmployeeRole roles) {
        return employeeRepo.findByRoles(roles);
    }

    public List<Employee> findByRolesAndNameContaining(Employee.EmployeeRole roles, String name) {
        return employeeRepo.findByRolesAndNameContaining(roles, name);
    }

    public Employee update(Employee requester, CreateEmployeeRequest ownerRequest) {

        // Fetching saved Record
        Employee savedOwner = employeeRepo
                .findByUsername(ownerRequest.getUsername())
                .orElseThrow(() -> new EmployeeNotFound(ownerRequest.getUsername()));

        // Validating Hierarchy
        if (HierarchyValidator.isHierarchyInvalid(requester.getRoles(), savedOwner.getRoles()))
            throw new PermissionDenied();

        // Password Update
        if (ownerRequest.getPassword() != null)
            savedOwner.setPassword(encoder.encode(ownerRequest.getPassword()));

        // Address Update
        if (ownerRequest.getAddress() != null)
            savedOwner.setAddress(ownerRequest.getAddress());

        // Update Role
        if (ownerRequest.getRoles() != null) {

            // Validating Hierarchy
            if (HierarchyValidator.isHierarchyInvalid(requester.getRoles(), ownerRequest.getRoles()))
                throw new PermissionDenied();

            savedOwner.setRoles(ownerRequest.getRoles());
        }

        // Updated at update
        savedOwner.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return employeeRepo.save(savedOwner);
    }

    public void deleteById(Employee requester, Integer id) {
        Employee savedEmployee = findById(id);

        if (HierarchyValidator.isHierarchyInvalid(requester.getRoles(), savedEmployee.getRoles()))
            throw new PermissionDenied();

        employeeRepo.deleteById(id);
    }
}