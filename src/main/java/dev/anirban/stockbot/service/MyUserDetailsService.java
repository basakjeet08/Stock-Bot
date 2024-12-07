package dev.anirban.stockbot.service;

import dev.anirban.stockbot.exception.EmployeeNotFound;
import dev.anirban.stockbot.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepo
                .findByUsername(username)
                .orElseThrow(() -> new EmployeeNotFound(username));
    }
}