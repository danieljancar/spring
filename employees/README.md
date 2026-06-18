# Employees

- `@SpringBootApplication` starts the app
- `@Entity` + Spring Data JPA store `Employee` rows in H2
- `@RestController` exposes `/employees`
- Spring Security protects write operations with HTTP Basic auth
- Tests verify the public and protected endpoints

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 21) mvn test
JAVA_HOME=$(/usr/libexec/java_home -v 21) mvn spring-boot:run
```

- `GET /employees` to list the seeded employees
- `POST /employees` with basic auth `admin / password`

Example:

```bash
curl http://localhost:8080/employees

curl -u admin:password \
  -H 'Content-Type: application/json' \
  -d '{"firstName":"Linus","lastName":"Torvalds"}' \
  http://localhost:8080/employees
```

## Mental model

- **Controller** = HTTP layer
- **Repository** = database layer
- **Entity** = data model
- **SecurityConfig** = who can call what
- **DataSeeder** = sample data on startup
