package dev.danieljancar.playground.employees.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class UpdateEmployeeDTO {

    @Size(min = 3, max = 50, message = "Firstname must be between {min} and {max} characters long")
    private String firstName;

    @Size(min = 3, max = 50, message = "Lastname must be between {min} and {max} characters long")
    private String lastName;

    @Min(value = 0, message = "Salary must be a positive number")
    private Integer salary;

    public UpdateEmployeeDTO() {
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
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
