package dev.danieljancar.playground.employees;

import dev.danieljancar.playground.employees.entities.Employee;
import dev.danieljancar.playground.employees.controllers.EmployeeController;
import dev.danieljancar.playground.employees.repositories.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class EmployeeControllerTests {

    private final EmployeeRepository repository = mock(EmployeeRepository.class);
    private final EmployeeController controller = new EmployeeController(repository);

    @Test
    void getEmployeesReturnsRepositoryResults() {
        when(repository.findAll()).thenReturn(List.of(
                new Employee("Ada", "Lovelace"),
                new Employee("Grace", "Hopper")
        ));

        List<Employee> result = controller.getAll();

        assertEquals(2, result.size());
        assertEquals("Ada", result.get(0).getFirstName());
        assertEquals("Hopper", result.get(1).getLastName());
    }

    @Test
    void createEmployeeReturnsCreatedResponse() {
        when(repository.save(any(Employee.class))).thenAnswer(invocation -> {
            Employee saved = invocation.getArgument(0);
            saved.setId(42L);
            return saved;
        });

        ResponseEntity<Employee> response = controller.create(new Employee("Linus", "Torvalds"));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(42L, response.getBody().getId());
        assertEquals("Linus", response.getBody().getFirstName());
    }

    @Test
    void getOneReturnsNotFoundWhenMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = controller.getOne(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteEmployeeDeletesExistingRow() {
        when(repository.existsById(5L)).thenReturn(true);

        ResponseEntity<Void> response = controller.delete(5L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(repository).deleteById(5L);
    }
}

