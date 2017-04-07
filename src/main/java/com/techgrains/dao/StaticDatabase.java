package com.techgrains.dao;

import com.techgrains.model.Department;
import com.techgrains.model.Designation;
import com.techgrains.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticDatabase {
    private static StaticDatabase instance = new StaticDatabase();

    private List<Employee> employees;
    private List<Department> departments;

    public static StaticDatabase getInstance() {
        return instance;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public List<Department> getAllDepartments() {
        return departments;
    }

    private Department getDepartmentByCode(int code) {
        for(Department department : departments)
            if(department.getCode() == code)
                return department;
        return null;
    }

    private StaticDatabase() {
        prepareDepartments();
        prepareEmployees();
    }

    private void prepareDepartments() {
        departments = new ArrayList<>();
        departments.add(prepareDepartment(10, "IT"));
        departments.add(prepareDepartment(11, "Marketing"));
        departments.add(prepareDepartment(12, "Services"));
        departments.add(prepareDepartment(13, "Products"));
        departments.add(prepareDepartment(14, "Staff"));
        departments.add(prepareDepartment(15, "Quality Assurance"));
        departments.add(prepareDepartment(16, "Finance"));
    }

    private void prepareEmployees() {
        employees = new ArrayList<>();
        employees.add(prepareEmployee(1, "Vishal", Designation.Director, Arrays.asList(getDepartmentByCode(10))));
        employees.add(prepareEmployee(2, "Amit", Designation.Director, Arrays.asList(getDepartmentByCode(11))));
        employees.add(prepareEmployee(3, "Ketan", Designation.Director, Arrays.asList(getDepartmentByCode(12), getDepartmentByCode(13), getDepartmentByCode(16))));
        employees.add(prepareEmployee(4, "Satish", Designation.Architect, Arrays.asList(getDepartmentByCode(12), getDepartmentByCode(13))));
        employees.add(prepareEmployee(5, "Vilesh", Designation.Architect, Arrays.asList(getDepartmentByCode(12), getDepartmentByCode(13))));
        employees.add(prepareEmployee(6, "Snehal", Designation.Manager, Arrays.asList(getDepartmentByCode(13))));
        employees.add(prepareEmployee(7, "Kashyap", Designation.Tester, Arrays.asList(getDepartmentByCode(12), getDepartmentByCode(13), getDepartmentByCode(15))));
        employees.add(prepareEmployee(8, "Sonali", Designation.Tester, Arrays.asList(getDepartmentByCode(14))));
    }

    private Employee prepareEmployee(long id, String name, Designation designation, List<Department> departments) {
        return new Employee(id, name, designation, departments);
    }

    private Department prepareDepartment(int code, String name) {
        return new Department(code, name);
    }
}
