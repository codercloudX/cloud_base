# 数据库持久化方案替换为MyBatis - 实现计划

## [x] 任务1: 添加MyBatis相关依赖
- **优先级**: P0
- **依赖**: 无
- **描述**:
  - 在pom.xml文件中添加MyBatis相关依赖
  - 移除Spring Data JPA依赖
- **成功标准**:
  - pom.xml文件中包含MyBatis依赖
  - Spring Data JPA依赖已移除
- **测试要求**:
  - `programmatic` TR-1.1: Maven构建成功，无依赖冲突
- **注意**:
  - 使用MyBatis-Plus可以简化开发，提供类似JPA的CRUD操作

## [x] 任务2: 创建MyBatis配置文件
- **优先级**: P0
- **依赖**: 任务1
- **描述**:
  - 创建MyBatis的配置文件
  - 配置数据源和其他MyBatis相关设置
- **成功标准**:
  - MyBatis配置文件创建成功
  - 配置正确，能够连接数据库
- **测试要求**:
  - `programmatic` TR-2.1: 应用启动时无MyBatis配置错误
- **注意**:
  - 可以使用Spring Boot的自动配置，简化配置过程

## [x] 任务3: 创建UserMapper接口和XML文件
- **优先级**: P1
- **依赖**: 任务2
- **描述**:
  - 创建UserMapper接口，定义CRUD操作
  - 创建UserMapper.xml文件，编写SQL语句
  - 实现与原JPA Repository相同的查询方法
- **成功标准**:
  - UserMapper接口和XML文件创建成功
  - 包含所有原JPA Repository的查询方法
- **测试要求**:
  - `programmatic` TR-3.1: UserMapper接口编译通过
  - `programmatic` TR-3.2: UserMapper.xml文件格式正确，无语法错误
- **注意**:
  - 确保SQL语句与原JPA查询逻辑一致

## [x] 任务4: 创建DepartmentMapper接口和XML文件
- **优先级**: P1
- **依赖**: 任务2
- **描述**:
  - 创建DepartmentMapper接口，定义CRUD操作
  - 创建DepartmentMapper.xml文件，编写SQL语句
- **成功标准**:
  - DepartmentMapper接口和XML文件创建成功
  - 包含所有必要的CRUD方法
- **测试要求**:
  - `programmatic` TR-4.1: DepartmentMapper接口编译通过
  - `programmatic` TR-4.2: DepartmentMapper.xml文件格式正确，无语法错误
- **注意**:
  - 确保SQL语句与原JPA查询逻辑一致

## [x] 任务5: 修改UserServiceImpl实现类
- **优先级**: P1
- **依赖**: 任务3
- **描述**:
  - 修改UserServiceImpl实现类，使用UserMapper替代UserRepository
  - 更新所有数据库操作方法
- **成功标准**:
  - UserServiceImpl实现类编译通过
  - 所有方法使用UserMapper进行数据库操作
- **测试要求**:
  - `programmatic` TR-5.1: UserServiceImpl实现类编译通过
  - `programmatic` TR-5.2: 应用启动时无错误
- **注意**:
  - 确保所有原有的业务逻辑保持不变

## [ ] 任务6: 修改DepartmentServiceImpl实现类
- **优先级**: P1
- **依赖**: 任务4
- **描述**:
  - 修改DepartmentServiceImpl实现类，使用DepartmentMapper替代DepartmentRepository
  - 更新所有数据库操作方法
- **成功标准**:
  - DepartmentServiceImpl实现类编译通过
  - 所有方法使用DepartmentMapper进行数据库操作
- **测试要求**:
  - `programmatic` TR-6.1: DepartmentServiceImpl实现类编译通过
  - `programmatic` TR-6.2: 应用启动时无错误
- **注意**:
  - 确保所有原有的业务逻辑保持不变

## [ ] 任务7: 测试所有功能
- **优先级**: P2
- **依赖**: 任务5, 任务6
- **描述**:
  - 测试登录功能
  - 测试用户管理功能
  - 测试部门管理功能
- **成功标准**:
  - 所有功能正常工作
  - 与原JPA实现的行为一致
- **测试要求**:
  - `programmatic` TR-7.1: 登录功能正常
  - `programmatic` TR-7.2: 用户列表查询正常
  - `programmatic` TR-7.3: 部门列表查询正常
  - `programmatic` TR-7.4: 部门树查询正常
- **注意**:
  - 测试时使用与原JPA实现相同的测试数据

## 总结

本计划旨在将数据库持久化方案从Spring Data JPA替换为MyBatis，保持所有原有功能不变。通过逐步替换依赖、配置和实现，确保系统的稳定性和一致性。

替换后，系统将使用MyBatis的XML配置方式编写SQL语句，提供更灵活的数据库操作能力，同时通过MyBatis-Plus简化CRUD操作的开发。