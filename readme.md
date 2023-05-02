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
- Separate out the department entity to improve data integrity scalibility and performance of the application data model ([Department](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/entities/Department.java), [Employyee](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/entities/Employee.java))
- Created DTO for Employee to simplify data exchange ([EmployeeDTO](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/entities/dto/EmployeeDto.java))
- Added h2 and datasource properties in Application.properties file ([Application.properties](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/resources/application.properties))
- Added in memory authentication ([SecurityConfig](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/configurations/SecurityConfig.java))
- Added Spring cache dependency, made list returning service functions cachable ([DepartmentServiceImpl](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/services/DepartmentServiceImpl.java), [EmployeeServiceImpl](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/services/EmployeeServiceImpl.java))
- Used ResponseEntity for HTTP responses in controlle classes instead of returning entity object directly ([DepartmentController](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/controllers/DepartmentController.java), [EmployeeController](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/controllers/EmployeeController.java))
- Added Log4j for logging ([EmployeeServiceImpl](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/services/EmployeeServiceImpl.java))
- Introduced field dependency over class dependency ([DepartmentController](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/controllers/DepartmentController.java), [EmployeeController](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/main/java/jp/co/axa/apidemo/controllers/EmployeeController.java))
- Added Unit Test for Department Controller and Service Class ([DepartmentServiceTest](https://github.com/Rabbia91/alj-java-challenge/blob/master/src/test/java/jp/co/axa/apidemo/services/DepartmentServiceTest.java))

### Other Suggestions
- Use the recommended secure authentication method such as OAuth2 or JWT, and to store the usernames and passwords securely, 
- Use exception handling to handle errors: by using Spring's @ControllerAdvice and @ExceptionHandler annotations to handle exceptions globally.

-----

### My experience in Java

My professional journey began as a Core Java Developer, where I honed my skills in Java and became proficient in using technologies like JWT and JSP. I even had the opportunity to dabble in Android development. With time, I gradually transitioned to working with Spring Boot applications, which allowed me to build robust and scalable web applications with ease. Over my 9+ years of experience, Java has been the prominent language that has enabled me to solve complex business problems and deliver high-quality software solutions to clients.
