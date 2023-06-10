package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable {

    public Employee()  {}
    public Employee(String name, Integer salary, String department)    {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Employee(Long id, String name, Integer salary, String department)    {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;

}
