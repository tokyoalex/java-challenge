package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames="employeeCache")
public class EmployeeServiceImpl implements EmployeeService{

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Cacheable(value="employeeCache", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        log.info("Fetching the employee " + employeeId);
        if(employeeRepository.existsById(employeeId)) {
            Optional<Employee> optEmp = employeeRepository.findById(employeeId);
            log.info("Successfully fetched employee " + employeeId);
            return optEmp.get();
        }
        return null;
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employeeCache", key = "#employeeId")
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    @CacheEvict(value="employeeCache", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}