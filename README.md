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

- I'm a currently a mobile application developer so use Java for native Android application development, 
however my previous role was full stack where I used Java with Android and Spring Boot for approximately a year, 
additionally used Java with the Play framework for about a year.


#### REST API Usage

- Base URL for running locally: http://localhost:8080/AxaWebApp/api/v1/
  (Defined in src/main/resources/application.properties)

- As per the original specifications, the following REST APIs are available for the EmployeeController

#### GET /employees
- HTTP method: GET
- Parameters: None
- Description: Retrieves all employees from the in-memory(h2) database  
- Return: json object containing a list of all employees, or an empty object if no employees exist, both with
HTTP status OK

#### GET /employees/{employeeId}
- HTTP method: GET
- Parameters: employeeId
- Description: Retrieves an employee from the in-memory(h2) database
- Return: json object containing the employee specified in the request with HTTP status OK, 
or an empty object if the employee isnt found with HTTP status NOT_FOUND

#### POST /employees
- HTTP method: POST
- Parameters: Employee object in json format, containing (String) name, (Integer) salary, (String) department
- Description: Adds an employee to the in-memory(h2) database
- Return: json object containing the employee specified in the request with HTTP status CREATED

#### DELETE /employees/{employeeId}
- HTTP method: DELETE
- Parameters: employeeId
- Description: Deletes an employee from the in-memory(h2) database
- Return: HTTP status ACCEPTED if deletion was successful, HTTP status of NOT_FOUND if the employee id doesnt exist 

#### PUT /employees/{employeeId}
- HTTP method: PUT
- Parameters: employeeId
- Description: Updates an existing employees data in the in-memory(h2) database
- Return: HTTP status OK if the update was successful, HTTP status of NOT_FOUND if the employee id doesnt exist 

### Employee Object
- Package to import: jp.co.axa.apidemo.entities
- Instantiated with the following constructor:
Employee(String name, Integer salary, String department)