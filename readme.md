## Employee Management System (Java Challange)

This project provides a RESTful API for managing employees in a company.

### Features
- Retrieve a list of all employees
- Retrieve a single employee by ID
- Retrieve employees by department name or employee name
- Create a new employee
- Update an existing employee
- Delete an employee

### Technologies Used

- Java 8
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 in-memory database
- Maven
- Mockito
- Junit5

### Setup
- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

### API Documentation 

- Swagger UI : http://localhost:8080/swagger-ui.html

### Database 
- H2 UI : http://localhost:8080/h2-console

### Modification made
- Added h2 and datasource properties in Application.properties file 
- Added Spring Security dependency 
- Added in memory authentication
- Added Spring cache dependency, made list returning service functions cachable
- Created separate entity for the department to improve data integrity scalibility and performance of the application data model
- Used ResponseEntity for HTTP responses in controlle classes instead of returning entity object directly
- Added Unit Test for Department Controller and Service Class
- Introduced field dependency over class dependency 

### Other Suggestions
- Use the recommended secure authentication method such as OAuth2 or JWT, and to store the usernames and passwords securely, 
- Use exception handling to handle errors: by using Spring's @ControllerAdvice and @ExceptionHandler annotations to handle exceptions globally.

-----

### My experience in Java

My professional journey began as a Core Java Developer, where I honed my skills in Java and became proficient in using technologies like JWT and JSP. I even had the opportunity to dabble in Android development. With time, I gradually transitioned to working with Spring Boot applications, which allowed me to build robust and scalable web applications with ease. Over my 9+ years of experience, Java has been the prominent language that has enabled me to solve complex business problems and deliver high-quality software solutions to clients.
