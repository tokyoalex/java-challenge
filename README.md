### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

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
- Added basic http authentication, with the username and password