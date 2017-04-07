package com.techgrains.controller;

import com.techgrains.model.Department;
import com.techgrains.model.Designation;
import com.techgrains.model.Employee;
import com.techgrains.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET, value = "/{employeeId}")
    Employee get(@PathVariable long employeeId) {
        return employeeService.findById(employeeId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{employeeId}/department/list")
    List<Department> getDepartments(@PathVariable long employeeId) {
        return employeeService.findById(employeeId).getDepartments();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    List<Employee> list(@RequestParam(value="name", required=false) String name, @RequestParam(value="designation", required=false) Designation designation, @RequestParam(value="departmentCode", required=false) Integer departmentCode) {
        return employeeService.listByNameDesignationAndDepartmentcode(name, designation, departmentCode);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    Employee create(@RequestParam(value="name", required=false) String name, @RequestParam(value="designation", required=false) Designation designation) {
        return employeeService.createEmployee(name, designation);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{employeeId}/department/add")
    Employee addDepartment(@PathVariable long employeeId, @RequestParam("department") int department) {
        return employeeService.addDepartments(employeeId, department);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{employeeId}")
    Employee save(@PathVariable long employeeId, @RequestParam("name") String name, @RequestParam("designation") Designation designation) {
        return employeeService.update(employeeId, name, designation);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{employeeId}")
    Employee update(@PathVariable long employeeId, @RequestParam(value="name", required=false) String name, @RequestParam(value="designation", required=false) Designation designation) {
        return employeeService.update(employeeId, name, designation);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{employeeId}")
    void deleteEmployee(@PathVariable long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{employeeId}/department/{department}")
    Employee deleteDepartment(@PathVariable long employeeId, @PathVariable int department) {
        return employeeService.deleteDepartment(employeeId, department);
    }

}
