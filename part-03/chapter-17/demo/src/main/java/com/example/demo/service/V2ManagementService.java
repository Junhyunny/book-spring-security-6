package com.example.demo.service;

import com.example.demo.domain.Employee;
import com.example.demo.repository.ManagementRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class V2ManagementService {

    private final ManagementRepository managementRepository;

    public V2ManagementService(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    @PreAuthorize("""
            hasRole('MANAGER')
                and #employee.managerId() == authentication.principal""")
    public void addEmployee(Employee employee) {
        managementRepository.addEmployee(employee);
    }

    @PostAuthorize("""
            returnObject.managerId() == authentication.principal
                or returnObject.id() == authentication.principal
            """)
    public Employee getEmployeeById(String id) {
        return managementRepository.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PreFilter("filterObject.managerId() == authentication.principal")
    public void addEmployees(List<Employee> employeeList) {
        managementRepository.addEmployees(employeeList);
    }

    @PostFilter("""
            filterObject.value.managerId() == authentication.principal
                or filterObject.value.id() == authentication.principal""")
    public Map<String, Employee> getMyEmployees() {
        return managementRepository.getAllEmployees();
    }
}
