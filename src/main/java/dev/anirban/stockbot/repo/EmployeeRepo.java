package dev.anirban.stockbot.repo;

import dev.anirban.stockbot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}