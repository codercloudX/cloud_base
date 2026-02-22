package com.cloudbase.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudbase.entity.Department;
import com.cloudbase.mapper.DepartmentMapper;
import com.cloudbase.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Map<String, Object> getDepartmentList(Map<String, Object> params) {
        int page = (int) params.getOrDefault("page", 1);
        int size = (int) params.getOrDefault("size", 10);

        IPage<Department> pageInfo = new Page<>(page, size);
        IPage<Department> departmentPage = departmentMapper.selectDepartmentList(pageInfo, params);

        Map<String, Object> result = new HashMap<>();
        result.put("records", departmentPage.getRecords());
        result.put("total", departmentPage.getTotal());
        result.put("current", departmentPage.getCurrent());
        result.put("size", departmentPage.getSize());
        result.put("pages", departmentPage.getPages());

        return result;
    }

    @Override
    public Map<String, Object> createDepartment(Department department) {
        Map<String, Object> result = new HashMap<>();
        try {
            department.setStatus("NORMAL");
            department.setCreatedAt(LocalDateTime.now());
            department.setUpdatedAt(LocalDateTime.now());
            department.setLevel(1);
            if (department.getParentId() != null) {
                // 计算层级
                Department parent = departmentMapper.selectById(department.getParentId());
                if (parent != null) {
                    department.setLevel(parent.getLevel() + 1);
                }
            }
            int rows = departmentMapper.insert(department);
            if (rows > 0) {
                result.put("success", true);
                result.put("message", "部门创建成功");
                result.put("data", department);
            } else {
                result.put("success", false);
                result.put("message", "部门创建失败");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "部门创建失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> updateDepartment(Long id, Department department) {
        Map<String, Object> result = new HashMap<>();
        try {
            department.setId(id);
            department.setUpdatedAt(LocalDateTime.now());
            if (department.getParentId() != null) {
                // 计算层级
                Department parent = departmentMapper.selectById(department.getParentId());
                if (parent != null) {
                    department.setLevel(parent.getLevel() + 1);
                }
            }
            int rows = departmentMapper.updateById(department);
            if (rows > 0) {
                result.put("success", true);
                result.put("message", "部门更新成功");
                result.put("data", department);
            } else {
                result.put("success", false);
                result.put("message", "部门更新失败");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "部门更新失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> getById(Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Department department = departmentMapper.selectById(id);
            if (department != null) {
                result.put("success", true);
                result.put("message", "获取部门成功");
                result.put("data", department);
            } else {
                result.put("success", false);
                result.put("message", "部门不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取部门失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteDepartment(Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Department department = departmentMapper.selectById(id);
            if (department != null) {
                department.setStatus("DELETED");
                int rows = departmentMapper.updateById(department);
                if (rows > 0) {
                    result.put("success", true);
                    result.put("message", "部门删除成功");
                } else {
                    result.put("success", false);
                    result.put("message", "部门删除失败");
                }
            } else {
                result.put("success", false);
                result.put("message", "部门不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "部门删除失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> batchOperate(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Long> ids = (List<Long>) params.getOrDefault("ids", Collections.emptyList());
            String operation = (String) params.getOrDefault("operation", "");

            int successCount = 0;
            int failureCount = 0;

            for (Long id : ids) {
                try {
                    switch (operation) {
                        case "delete":
                            deleteDepartment(id);
                            successCount++;
                            break;
                        default:
                            failureCount++;
                    }
                } catch (Exception e) {
                    failureCount++;
                }
            }

            result.put("success", true);
            result.put("message", "批量操作完成");
            result.put("successCount", successCount);
            result.put("failureCount", failureCount);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "批量操作失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> getDepartmentTree() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Department> allDepartments = departmentMapper.selectAllDepartments();
            List<Department> departmentTree = buildDepartmentTree(allDepartments);
            result.put("success", true);
            result.put("message", "获取部门树成功");
            result.put("data", departmentTree);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取部门树失败: " + e.getMessage());
        }
        return result;
    }

    private List<Department> buildDepartmentTree(List<Department> departments) {
        Map<Long, Department> departmentMap = new HashMap<>();
        List<Department> rootDepartments = new ArrayList<>();

        // 将所有部门放入Map
        for (Department dept : departments) {
            departmentMap.put(dept.getId(), dept);
        }

        // 构建树结构
        for (Department dept : departments) {
            if (dept.getParentId() == null) {
                // 根部门
                rootDepartments.add(dept);
            }
        }

        return rootDepartments;
    }
}
