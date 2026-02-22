package com.cloudbase.service;

import com.cloudbase.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Map<String, Object> getDepartmentList(Map<String, Object> params);
    Map<String, Object> createDepartment(Department department);
    Map<String, Object> updateDepartment(Long id, Department department);
    Map<String, Object> getById(Long id);
    Map<String, Object> deleteDepartment(Long id);
    Map<String, Object> batchOperate(Map<String, Object> params);
    Map<String, Object> getDepartmentTree();
}
