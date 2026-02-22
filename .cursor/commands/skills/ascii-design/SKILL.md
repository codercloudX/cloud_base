---
name: ascii-designer
description: Create professional ASCII UI wireframes, mockups, and prototypes for software, web,and mobile interfaces. Supports various styles including standard, modular, and compact dashboard views
version: 0.0.1
license: Shinichi

---

# ASCII Design

专业的ASCII界面原型设计工具，能够根据需求描述或字段列表，快速生成软件界面、安装向导、网页布局及移动端App线框图。

**核心特性**

- 自动保存设计稿到项目根目录的 `ascii-ui-design/` 文件夹
- 支持多种界面风格和布局模式
- 提供完整的设计说明和文档

--- ----



### 工作流程(Workflow)

本技能遵循以下工作流程:
```
用户需求生成 -> ASCII设计稿 -> 自动保存到ascii-ui-design/ -> 输出设计说明
```


#### 1.需求分析

根据用户描述或字段列表，理解界面类型、功能需求和风格偏好
#### 2.设计生成

使用ASCII字符绘制界面线框图，遵循设计原则和组件规范
#### 3.自动保存

**重要: **每次生成设计稿后，必须自动保存到项目根目录的`ascii-ui-design` 文件夹

- 文件命名规则：`{页面名称}_{时间戳}.txt`或 `{页面名称}_v(版本号).txt`
- 示例：`login_page_20250112.txt` 、`ticket_detail_v1.txt`
- 如果文件夹不存在，先创建文件夹

#### 4.设计说明

提供设计思路、关键元素、风格选择等说明文字