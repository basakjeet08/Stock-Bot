package dev.anirban.stockbot.repo;

import dev.anirban.stockbot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);

    List<Employee> findByNameContaining(String name);

    List<Employee> findByRoles(Employee.EmployeeRole roles);

    List<Employee> findByRolesAndNameContaining(Employee.EmployeeRole roles, String name);
}