package dev.anirban.stockbot.service;

import dev.anirban.stockbot.dto.request.CreateEmployeeRequest;
import dev.anirban.stockbot.dto.response.TokenWrapper;
import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeService employeeService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public TokenWrapper login(CreateEmployeeRequest employee) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        employee.getUsername(), employee.getPassword()
                )
        );

        Employee savedEmployee = employeeService.findByUsername(employee.getUsername());
        return new TokenWrapper(jwtService.generateToken(savedEmployee));
    }
}