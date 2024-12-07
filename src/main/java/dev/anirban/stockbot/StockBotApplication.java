package dev.anirban.stockbot;

import dev.anirban.stockbot.dto.request.CreateEmployeeRequest;
import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
public class StockBotApplication {

    private final OwnerService service;

    public static void main(String[] args) {
        SpringApplication.run(StockBotApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            CreateEmployeeRequest ownerRequest = CreateEmployeeRequest
                    .builder()
                    .name("Owner Name")
                    .username("Owner Username")
                    .password("Owner Password")
                    .address("Owner Address")
                    .salary(0)
                    .joiningDate(Timestamp.valueOf(LocalDateTime.now()))
                    .roles(Employee.EmployeeRole.OWNER)
                    .build();

            service.create(ownerRequest);
        };
    }
}