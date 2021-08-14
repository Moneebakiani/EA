package edu.miu.cs544.springstartproject.service;

import edu.miu.cs544.springstartproject.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(Integer id);
    Department findByName(String name);
}
