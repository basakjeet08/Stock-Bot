package dev.anirban.stockbot.dto.common;

import dev.anirban.stockbot.entity.Employee;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Integer id;
    private String name;
    private String username;
    private Integer salary;
    private Timestamp joiningDate;
    private Employee.EmployeeRole roles;
}