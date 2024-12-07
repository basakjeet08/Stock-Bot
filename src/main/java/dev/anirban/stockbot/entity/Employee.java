package dev.anirban.stockbot.entity;


import dev.anirban.stockbot.dto.common.EmployeeDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    public enum EmployeeRole {
        OWNER, MANAGER, CASHIER, STAFF
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "joining_date", nullable = false)
    private Timestamp joiningDate;

    @Column(name = "roles", nullable = false)
    private EmployeeRole roles;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public EmployeeDto toEmployeeDto() {
        return EmployeeDto
                .builder()
                .id(id)
                .name(name)
                .username(username)
                .salary(salary)
                .joiningDate(joiningDate)
                .roles(roles)
                .build();
    }
}