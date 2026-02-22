package com.cloudbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloudbase.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据部门ID查询用户列表
     */
    IPage<User> selectByDepartmentId(IPage<User> page, Long departmentId);

    /**
     * 根据姓名或员工ID模糊查询用户列表
     */
    IPage<User> selectByNameOrEmployeeId(IPage<User> page, String name, String employeeId);

    /**
     * 根据姓名或员工ID模糊查询并按部门ID过滤用户列表
     */
    IPage<User> selectByNameOrEmployeeIdAndDepartmentId(IPage<User> page, String name, String employeeId, Long departmentId);

    /**
     * 根据姓名或员工ID查询单个用户
     */
    User selectOneByNameOrEmployeeId(String name, String employeeId);

    /**
     * 根据条件查询用户列表
     */
    IPage<User> selectUserList(IPage<User> page, Map<String, Object> params);
}
