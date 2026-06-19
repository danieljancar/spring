package dev.danieljancar.playground.employees.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.danieljancar.playground.employees.dto.UpdateEmployeeDTO;
import dev.danieljancar.playground.employees.entities.Employee;
import dev.danieljancar.playground.employees.repositories.EmployeeRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity <Integer> getMonthlySalary(@PathVariable Long id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent() && employee.get().hasSalary()) {
            return ResponseEntity.ok(employee.get().getSalary() / 12);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        Employee saved = repository.save(employee);
        URI location = URI.create("/employees/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeDTO input) {
        return repository.findById(id)
                .map(existing -> {
                    if (input.getFirstName() != null) {
                        existing.setFirstName(input.getFirstName());
                    }
                    if (input.getLastName() != null) {
                        existing.setLastName(input.getLastName());
                    }
                    if (input.getSalary() != null) {
                        existing.setSalary(input.getSalary());
                    }
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

