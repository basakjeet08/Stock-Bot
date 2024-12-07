package dev.anirban.stockbot.dto.request;

import dev.anirban.stockbot.entity.Employee;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    private String name;
    private String username;
    private String password;
    private String address;
    private Integer salary;
    private Timestamp joiningDate;
    private Employee.EmployeeRole roles;
}