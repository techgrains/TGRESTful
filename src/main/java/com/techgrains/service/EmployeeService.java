package com.techgrains.service;

import com.techgrains.dao.EmployeeDAO;
import com.techgrains.exception.TGConflictException;
import com.techgrains.exception.TGForbiddenException;
import com.techgrains.exception.TGNotFoundException;
import com.techgrains.model.Department;
import com.techgrains.model.Designation;
import com.techgrains.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;

    public Employee findById(long id) throws TGNotFoundException {
        Employee employee = employeeDAO.getEmployeeById(id);
        if(employee == null)
            throw new TGNotFoundException("Employee not found for id - " + id);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public List<Department> getAllDepartments() {
        return employeeDAO.getAllDepartments();
    }

    public List<Employee> listByNameDesignationAndDepartmentcode(String name, Designation designation, Integer departmentCode) {
        List<Employee> employees = new ArrayList<>();

        for(Employee employee : employeeDAO.getAllEmployees())
            if(employee.hasNameDesignationDepartment(name, designation, departmentCode))
                employees.add(employee);

        return employees;
    }

    public Employee createEmployee(String name, Designation designation) {
        Employee employee = employeeDAO.getEmployeeByName(name);
        if(employee == null) {
            return new Employee(999, name, designation);
        } else {
            throw new TGConflictException("Employee already exists for name - " + name);
        }
    }

    public Employee addDepartments(long id, int departmentCode) {
        Employee employee = findById(id);
        if (!employeeDAO.isDepartmentExists(departmentCode))
            throw new TGNotFoundException("Department not found for code - " + departmentCode);

        if(employee.hasDepartment(departmentCode))
            throw new TGForbiddenException("Employee already has department code - " + departmentCode);

        employee = employee.copy(); // To avoid changes in static database instance

        Department department = employeeDAO.getDepartment(departmentCode);
        if(department!=null)
            employee.addDepartment(department);

        return employee;
    }

    public Employee update(long id, String name, Designation designation) {
        Employee employee = findById(id).copy(); // To avoid changes in static database instance
        if(name!=null && name.length()>0) {
            Employee existing = employeeDAO.getEmployeeByName(name);
            if(existing == null)
                employee.setName(name);
            else
                throw new TGConflictException("Employee already exists for name - " + name);
        }

        if(designation!=null) {
            if(employee.getDesignation().equals(Designation.Director))
                throw new TGForbiddenException("System can't change designation of the Director.");
            employee.setDesignation(designation);
        }
        return employee;
    }

    public void deleteEmployee(long id) {
        Employee employee = findById(id);
        // Do nothing - don't do any change in static database instance
    }

    public Employee deleteDepartment(long id, int departmentCode) {
        Employee employee = findById(id);
        if(employee.hasDepartment(departmentCode)) {
            employee = employee.copy();
            employee.removeDepartment(departmentCode);
        } else {
            throw new TGNotFoundException("Employee doesn't have department having code - " + departmentCode);
        }
        return employee;
    }
}
