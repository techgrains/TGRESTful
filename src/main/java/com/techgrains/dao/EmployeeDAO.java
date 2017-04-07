package com.techgrains.dao;

import com.techgrains.model.Department;
import com.techgrains.model.Designation;
import com.techgrains.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO {

    public Employee getEmployeeById(long id) {
        for(Employee employee : StaticDatabase.getInstance().getAllEmployees())
            if(employee.getId() == id)
                return employee;
        return null;
    }

    public List<Employee> getAllEmployees() {
        return StaticDatabase.getInstance().getAllEmployees();
    }

    public List<Department> getAllDepartments() {
        return StaticDatabase.getInstance().getAllDepartments();
    }

    public List<Employee> getEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<>();
        for(Employee employee : StaticDatabase.getInstance().getAllEmployees())
            if(employee.getName().equals(name))
                employees.add(employee);
        return employees;
    }

    public List<Employee> getEmployeesByDesignation(Designation designation) {
        List<Employee> employees = new ArrayList<>();
        for(Employee employee : StaticDatabase.getInstance().getAllEmployees())
            if(employee.getDesignation().equals(designation))
                employees.add(employee);
        return employees;
    }

    public List<Employee> getEmployeesByDepartment(int code) {
        List<Employee> employees = new ArrayList<>();
        for(Employee employee : StaticDatabase.getInstance().getAllEmployees())
            for(Department department : employee.getDepartments())
                if(department.getCode() == code)
                    employees.add(employee);
        return employees;
    }

    public Employee getEmployeeByName(String name) {
        for(Employee employee : StaticDatabase.getInstance().getAllEmployees())
            if(employee.getName().equals(name))
                return employee;
        return null;
    }

    public boolean isDepartmentExists(Integer departmentCode) {
        for(Department department : getAllDepartments())
            if(department.getCode() == departmentCode)
                return true;
        return false;
    }

    public Department getDepartment(Integer departmentCode) {
        for(Department department : getAllDepartments())
            if(department.getCode() == departmentCode)
                return department;
        return null;
    }
}
