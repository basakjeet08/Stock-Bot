package dev.anirban.stockbot;

import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;

@SpringBootApplication
@RequiredArgsConstructor
public class StockBotApplication {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(StockBotApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            Employee newOwner = Employee
                    .builder()
                    .name("Owner Name")
                    .username("Owner Username")
                    .password(encoder.encode("Owner Password"))
                    .address("Owner Address")
                    .salary(0)
                    .joiningDate(Timestamp.valueOf(LocalDateTime.now()))
                    .roles(Employee.EmployeeRole.OWNER)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .restockList(new HashSet<>())
                    .build();

            employeeRepo.save(newOwner);
        };
    }
}