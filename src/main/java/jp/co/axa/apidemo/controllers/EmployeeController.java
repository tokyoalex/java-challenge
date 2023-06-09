package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        log.info("Get all employees");

        List<Employee> employees = employeeService.retrieveEmployees();
        log.info("Employee count: " +employees.size());
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        log.info("Get employee id: "+ employeeId);

        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null) {
            log.info("Get success");
            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        }

        log.warn("Get failed, employee not found");
        return new ResponseEntity<Employee>(new Employee(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee>  saveEmployee(@RequestBody Employee employee){
        log.info("Saving employee "+ employee.getName());
        Employee emp = employeeService.saveEmployee(employee);
        return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        log.info("Deleting employee id: " + employeeId);
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.deleteEmployee(employeeId);
            log.info("Successfully deleted");
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }

        log.warn("Delete failed, employee not found");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        log.info("Update employee id: " + employeeId);
        Employee emp = employeeService.getEmployee(employeeId);

        if(emp != null){
            log.info("Successfully updated");
            return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
        }
        log.warn("Update failed, employee not found");
        return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
    }

}
