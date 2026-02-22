package com.cloudbase.controller;

import com.cloudbase.entity.Department;
import com.cloudbase.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public Map<String, Object> getDepartmentList(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String keyword) {
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("page", page);
        params.put("size", size);
        params.put("keyword", keyword);
        return departmentService.getDepartmentList(params);
    }

    @PostMapping
    public Map<String, Object> createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getDepartmentById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }

    @PostMapping("/batch")
    public Map<String, Object> batchOperate(@RequestBody Map<String, Object> params) {
        return departmentService.batchOperate(params);
    }

    @GetMapping("/tree")
    public Map<String, Object> getDepartmentTree() {
        return departmentService.getDepartmentTree();
    }

}
