package com.techgrains.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee {

    private long id;
    private String name;
    private Designation designation;
    private List<Department> departments;
    private Date created;
    private Date modified;

    public Employee() {
    }

    public Employee(long id, String name, Designation designation) {
        this(id, name, designation, new ArrayList<>());
    }

    public Employee(long id, String name, Designation designation, List<Department> departments) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.departments = departments;
        this.created = new Date();
        this.modified = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public void updateModified() {
        this.modified = new Date();
    }

    public boolean hasDepartment(int code) {
        for (Department department : departments)
            if (department.getCode() == code)
                return true;
        return false;
    }

    public void addDepartment(Department department) {
        if(!hasDepartment(department.getCode()))
            departments.add(department);
    }

    public boolean hasNameDesignationDepartment(String name, Designation designation, Integer code) {
        boolean matchName = true;
        if(name!=null && name.length()>0)
            if (!this.name.contains(name))
                matchName = false;

        boolean matchDesignation = true;
        if(designation!=null)
            if(!this.designation.equals(designation))
                matchDesignation = false;

        boolean matchCode = true;
        if(code!=null && code.intValue()>0)
            if(!hasDepartment(code.intValue()))
                matchCode = false;

        return matchName && matchDesignation && matchCode;
    }

    public Employee copy() {
        return new Employee(this.id, this.name, this.designation, new ArrayList<>(this.departments));
    }

    public void removeDepartment(int departmentCode) {
        if(hasDepartment(departmentCode)) {
            for (Department department : departments)
                if (department.getCode() == departmentCode) {
                    departments.remove(department);
                    return;
                }
        }
    }
}
