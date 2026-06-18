package dev.danieljancar.playground.employees.repositories;

import dev.danieljancar.playground.employees.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}