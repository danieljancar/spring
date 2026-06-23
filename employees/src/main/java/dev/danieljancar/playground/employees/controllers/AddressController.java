package dev.danieljancar.playground.employees.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.danieljancar.playground.employees.constants.ApiConstants;
import dev.danieljancar.playground.employees.entities.Address;
import dev.danieljancar.playground.employees.repositories.AddressRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.ADDRESSES_BASE_PATH)
public class AddressController {
    private final AddressRepository repository;

    public AddressController(AddressRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Address> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getOne(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @Valid @RequestBody Address input) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setStreet(input.getStreet());
                    existing.setCity(input.getCity());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
