package dev.danieljancar.playground.employees.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50, message = "Firstname must be between {min} and {max} characters long")
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 50, message = "Lastname must be between {min} and {max} characters long")
    @Column(nullable = false)
    private String lastName;

    @Min(value = 0, message = "Salary must be a positive number")
    @Column(nullable = true)
    private Integer salary;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    protected Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(Integer yearlySalary) {
        this.salary = yearlySalary;
    }

    public boolean hasSalary() {
        return this.salary != null;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setEmployee(this);
        }
    }
}