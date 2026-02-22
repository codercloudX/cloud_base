package com.cloudbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloudbase.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * 根据条件查询部门列表
     */
    IPage<Department> selectDepartmentList(IPage<Department> page, Map<String, Object> params);

    /**
     * 查询所有部门
     */
    List<Department> selectAllDepartments();
}
