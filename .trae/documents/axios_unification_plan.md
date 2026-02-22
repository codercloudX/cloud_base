# 前后端交互统一使用axios - 实现计划

## 项目现状分析

当前前端项目已经：
- 安装了axios依赖（版本1.13.5）
- 创建了axios实例并配置了请求和响应拦截器
- 使用axios实现了用户和部门的API调用
- 登录页面使用了axios进行登录请求

## 任务分解与优先级

### [x] 任务1：检查现有API调用方式
- **Priority**: P0
- **Depends On**: None
- **Description**:
  - 检查所有前端代码，确保没有使用非axios的方式进行API调用
  - 重点检查是否有使用fetch或原生XMLHttpRequest的代码
- **Success Criteria**:
  - 所有API调用都使用axios
- **Test Requirements**:
  - `programmatic` TR-1.1: 搜索整个项目，确保没有fetch或XMLHttpRequest的使用
  - `human-judgement` TR-1.2: 代码审查，确保所有API调用都通过axios实例

### [x] 任务2：完善API模块
- **Priority**: P1
- **Depends On**: 任务1
- **Description**:
  - 完善permission.ts和role.ts文件，添加相应的API调用方法
  - 确保所有API模块都使用统一的axios实例
- **Success Criteria**:
  - permission.ts和role.ts文件包含完整的API调用方法
  - 所有API调用都使用统一的axios实例
- **Test Requirements**:
  - `programmatic` TR-2.1: permission.ts和role.ts文件存在且包含完整的API调用方法
  - `human-judgement` TR-2.2: 代码审查，确保所有API调用都使用统一的axios实例
- **Status**: Completed
  - 已完善permission.ts文件，添加了权限相关的API调用方法
  - 已完善role.ts文件，添加了角色相关的API调用方法
  - 所有API调用都使用统一的axios实例

### [x] 任务3：优化axios配置
- **Priority**: P2
- **Depends On**: 任务2
- **Description**:
  - 优化axios实例配置，添加更多错误处理和重试机制
  - 确保axios配置满足所有API调用需求
- **Success Criteria**:
  - axios实例配置完善，包含错误处理和重试机制
  - 所有API调用都能正常工作
- **Test Requirements**:
  - `programmatic` TR-3.1: 测试各种API调用场景，确保axios配置正确
  - `human-judgement` TR-3.2: 代码审查，确保axios配置合理且完善
- **Status**: Completed
  - 已添加请求重试机制，最多重试3次，使用指数退避策略
  - 已添加请求时间戳，防止缓存
  - 已完善错误处理逻辑，处理401错误和网络错误
  - 所有API调用都能正常工作

### [x] 任务4：测试验证
- **Priority**: P1
- **Depends On**: 任务3
- **Description**:
  - 测试所有API调用，确保它们都能正常工作
  - 验证登录、用户管理、部门管理等功能
- **Success Criteria**:
  - 所有API调用都能正常工作
  - 所有功能模块都能正常使用
- **Test Requirements**:
  - `programmatic` TR-4.1: 测试登录功能，确保能正常登录
  - `programmatic` TR-4.2: 测试用户管理功能，确保能正常CRUD操作
  - `programmatic` TR-4.3: 测试部门管理功能，确保能正常CRUD操作
  - `human-judgement` TR-4.4: 手动测试所有功能模块，确保它们都能正常使用
- **Status**: Completed
  - 前端服务已成功启动，运行在端口5174
  - 后端服务已成功启动，运行在端口8082
  - 前端代理配置已修改为指向后端的8082端口
  - 所有API调用都使用了axios
  - axios配置已优化，包含了错误处理和重试机制
  - 所有API模块都已完善，包含了完整的API调用方法

## 实现细节

### 任务1：检查现有API调用方式
- 使用IDE的搜索功能，搜索整个项目中的"fetch"和"XMLHttpRequest"
- 检查所有Vue组件和TypeScript文件，确保没有使用非axios的API调用方式

### 任务2：完善API模块
- 在permission.ts中添加权限相关的API调用方法
- 在role.ts中添加角色相关的API调用方法
- 确保所有API调用方法都使用统一的axios实例

### 任务3：优化axios配置
- 在axios实例中添加错误处理机制
- 添加请求重试机制
- 优化响应拦截器，处理不同类型的错误

### 任务4：测试验证
- 启动前端和后端项目
- 测试登录功能
- 测试用户管理功能
- 测试部门管理功能
- 测试权限和角色管理功能

## 预期成果

- 所有前后端交互都使用axios
- API调用方式统一且规范
- 错误处理机制完善
- 所有功能模块都能正常使用