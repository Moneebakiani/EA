package edu.miu.cs544.springstartproject.service;

import edu.miu.cs544.springstartproject.model.Department;
import edu.miu.cs544.springstartproject.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> findAll() {
        return repository.findAll();
    }

    public Department findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Department findByName(String name) {
        return repository.findByName(name).orElse(null);
    }
}
