package com.techgrains.controller;

import com.techgrains.model.Department;
import com.techgrains.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "department")
public class DepartmentController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    List<Department> list() {
        try {
            return employeeService.getAllDepartments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
