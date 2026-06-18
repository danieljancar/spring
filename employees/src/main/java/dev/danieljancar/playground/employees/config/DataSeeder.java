package dev.danieljancar.playground.employees.config;

import dev.danieljancar.playground.employees.entities.Employee;
import dev.danieljancar.playground.employees.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedEmployees(EmployeeRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Employee("Ada", "Lovelace"),
                        new Employee("Grace", "Hopper"),
                        new Employee("Alan", "Turing")
                ));
            }
        };
    }
}

