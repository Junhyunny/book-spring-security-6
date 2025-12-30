package com.example.demo.service;

import com.example.demo.domain.Employee;
import com.example.demo.repository.ManagementRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@PreAuthorize("denyAll()")
@Service
public class V1ManagementService {

    private final ManagementRepository managementRepository;

    public V1ManagementService(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    public Map<String, Employee> getMyEmployees() {
        return managementRepository.getAllEmployees();
    }

    public Employee getEmployeeById(String id) {
        return managementRepository.getEmployeeById(id);
    }

    public void addEmployee(Employee employee) {
        managementRepository.addEmployee(employee);
    }

    public void addEmployees(List<Employee> employeeList) {
        managementRepository.addEmployees(employeeList);
    }
}
