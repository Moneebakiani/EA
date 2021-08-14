package edu.miu.cs544.springstartproject.controller;

import edu.miu.cs544.springstartproject.model.Department;
import edu.miu.cs544.springstartproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping("")
    public List<Department> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping("/search")
    public Department findByName(@RequestParam("name") String name){
        return service.findByName(name);
    }
}
