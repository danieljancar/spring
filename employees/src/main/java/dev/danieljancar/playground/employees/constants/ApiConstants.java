package dev.danieljancar.playground.employees.constants;

public final class ApiConstants {

    private ApiConstants() {
        // Prevent instantiation
    }

    // Base paths
    public static final String EMPLOYEES_BASE_PATH = "/employees";
    public static final String ADDRESSES_BASE_PATH = "/addresses";

    // Employee endpoints
    public static final String EMPLOYEE_BY_ID = "/{id}";
    public static final String EMPLOYEE_SALARY = "/{id}/salary";

    // Address endpoints
    public static final String ADDRESS_BY_ID = "/{id}";
}
