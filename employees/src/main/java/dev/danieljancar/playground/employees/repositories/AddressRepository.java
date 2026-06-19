package dev.danieljancar.playground.employees.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.danieljancar.playground.employees.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {}