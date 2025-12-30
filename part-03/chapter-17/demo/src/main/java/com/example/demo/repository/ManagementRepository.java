package com.example.demo.repository;

import com.example.demo.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ManagementRepository {

    private Map<String, Employee> employees;

    public ManagementRepository() {
        init();
    }

    public void init() {
        this.employees = new ConcurrentHashMap<>();
        this.employees.put("S0001", new Employee("S0001", "S0001", "supervisor"));
        this.employees.put("M0001", new Employee("M0001", "S0001", "junhyunny"));
        this.employees.put("M0002", new Employee("M0002", "S0001", "tangerine"));
        this.employees.put("E0001", new Employee("E0001", "M0001", "jua"));
        this.employees.put("E0002", new Employee("E0002", "M0002", "tory"));
    }

    public Map<String, Employee> getAllEmployees() {
        return new ConcurrentHashMap<>(employees);
    }

    public Employee getEmployeeById(String id) {
        var employee = employees.get(id);
        if (employee == null) {
            throw new RuntimeException("employee not found");
        }
        return employee;
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.id(), employee);
    }

    public void addEmployees(List<Employee> employeeList) {
        for (var employee : employeeList) {
            employees.put(employee.id(), employee);
        }
    }
}
