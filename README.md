### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.

#### Experience in Java

- I frequently use multiple languages; Java 8 years, Spring Boot (several months during previous role)
I've also used other similar frameworks such as Play and CakePhp


#### Employee Object

The object being sent in requests/ responses holding Name, Salary and department of an employee
- Package: jp.co.axa.apidemo.entities
- 2 Constructors:
  - *1 Employee(String name, Integer salary, String department)
  - *2 Employee(Long Id, String name, Integer salary, String department)


#### REST API Usage

- Base URL for running locally: http://localhost:8080/AxaWebApp/api/v1/
  (Defined in src/main/resources/application.properties)

- As per the original specifications, the following REST APIs are available for EmployeeController

#### GET /employees
- HTTP method: GET
- Parameters: None
- Description: Retrieves all employees from the in-memory(h2) database  
- Return value: Json object containing a list of all [Employee objects](#employee-object)(*1), 
or an empty object if no employees exist, both with HTTP status OK

#### GET /employees/{employeeId}
- HTTP method: GET
- Parameters: employeeId
- Description: Retrieves an employee from the in-memory(h2) database
- Return value: [Employee object](#employee-object)(*1) in Json format, with HTTP status OK, 
or an empty object if the employee isnt found with HTTP status NOT_FOUND

#### POST /employees
- HTTP method: POST
- Parameters: [Employee object](#employee-object)(*1) in Json format
- Description: Adds an employee to the in-memory(h2) database
- Return value: [Employee object](#employee-object)(*1) in Json format, with HTTP status CREATED

#### DELETE /employees/{employeeId}
- HTTP method: DELETE
- Parameters: employeeId
- Description: Deletes an employee from the in-memory(h2) database
- Return value: HTTP status ACCEPTED if deletion was successful, HTTP status of NOT_FOUND if the employee id doesnt exist 

#### PUT /employees/{employeeId}
- HTTP method: PUT
- Parameters: employeeId, [Employee object](#employee-object)(*2) in Json format
- Description: Updates an existing employees data in the in-memory(h2) database
- Return value: HTTP status OK if the update was successful, HTTP status of NOT_FOUND if the employee id doesnt exist 

#### Miscellaneous
- Added caching using ehCache. You can check the status of the ehCache via Jconsole, when the Spring boot application is running
- Added basic http authentication, with the username/ password defined in respectively as SecurityConfiguration.java as user/user456
- Added unit tests for each REST API including those to test some failures
- data.sql is used in Unit testing to populate the database with a few sample employees

#### Restrictions
- use java 8
- This restricts Spring Boot to version 5.1.6 as well as other dependencies 

