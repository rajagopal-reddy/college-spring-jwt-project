package com.college.directory.service;

import com.college.directory.model.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department updateDepartment(Long id, Department departmentDetails);
    void deleteDepartment(Long id);
}

