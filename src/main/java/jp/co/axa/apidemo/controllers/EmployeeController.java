package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        System.out.println("\nGet all employees");
        List<Employee> employees = employeeService.retrieveEmployees();
        System.out.println("\nEmployee count: " +employees.size());
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        System.out.println("\nGet employee id: "+ employeeId);
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null) {
            System.out.println("\nGet success");
            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        }
        System.out.println("\nGet failed, employee not found");
        return new ResponseEntity<Employee>(new Employee(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee>  saveEmployee(@RequestBody Employee employee){
        System.out.println("\nSaving employee "+ employee.getName());
        Employee emp = employeeService.saveEmployee(employee);
        return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        System.out.println("\nDeleting employee id: " + employeeId);
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.deleteEmployee(employeeId);
            System.out.println("\nSuccessfully deleted");
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        System.out.println("\nDelete failed, employee not found");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        System.out.println("\nUpdate employee id: " + employeeId);
        Employee emp = employeeService.getEmployee(employeeId);

        if(emp != null){
            System.out.println("\nSuccessfully updated");
            return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
        }
        System.out.println("\nUpdate failed, employee not found");
        return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
    }

}
