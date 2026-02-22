<template>
  <div class="user-management">
    <h2>用户管理</h2>
    
    <!-- 搜索和操作栏 -->
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="12">
        <el-input v-model="searchForm.keyword" placeholder="搜索姓名或工号" style="width: 300px; margin-right: 10px;">
          <template #append>
            <el-button @click="handleSearch">查询</el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :span="12" style="text-align: right;">
        <el-button type="primary" @click="handleAdd">新增</el-button>
        <el-button @click="handleBatchOperation">批量操作</el-button>
      </el-col>
    </el-row>

    <!-- 用户列表 -->
    <el-table :data="userList" style="width: 100%">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="employeeId" label="工号" width="120"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="180"></el-table-column>
      <el-table-column prop="phone" label="电话" width="120"></el-table-column>
      <el-table-column label="部门" width="120">
        <template #default="scope">
          {{ getDepartmentName(scope.row.departmentId) }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.status === 'ACTIVE' ? 'success' : scope.row.status === 'DISABLED' ? 'warning' : 'danger'">
            {{ scope.row.status === 'ACTIVE' ? '活跃' : scope.row.status === 'DISABLED' ? '禁用' : '已注销' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" @click="handleResetPassword(scope.row.id)">重置密码</el-button>
          <el-button 
            size="small" 
            :type="scope.row.status === 'ACTIVE' ? 'warning' : 'success'"
            @click="handleDisable(scope.row.id, scope.row.status === 'ACTIVE')"
          >
            {{ scope.row.status === 'ACTIVE' ? '禁用' : '启用' }}
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">注销</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      v-model:current-page="pagination.current"
      v-model:page-size="pagination.size"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="margin-top: 20px; text-align: right;"
    />

    <!-- 新增/编辑对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogType === 'add' ? '新增用户' : '编辑用户'"
      width="500px"
    >
      <el-form :model="form" label-width="80px">
        <el-form-item label="姓名" required>
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="工号" required>
          <el-input v-model="form.employeeId"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="部门">
          <el-select v-model="form.departmentId" placeholder="选择部门" @change="handleDepartmentChange">
            <el-option label="无" :value="null"></el-option>
            <el-option
              v-for="dept in departmentTree"
              :key="dept.id"
              :label="dept.name"
              :value="dept.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" v-if="dialogType === 'edit'">
          <el-select v-model="form.status">
            <el-option label="活跃" value="ACTIVE"></el-option>
            <el-option label="禁用" value="DISABLED"></el-option>
            <el-option label="已注销" value="DELETED"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmit">确定</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 批量操作对话框 -->
    <el-dialog
      v-model="batchDialogVisible"
      title="批量操作"
      width="400px"
    >
      <el-form :model="batchForm" label-width="80px">
        <el-form-item label="操作类型" required>
          <el-select v-model="batchForm.operation">
            <el-option label="重置密码" value="resetPassword"></el-option>
            <el-option label="禁用" value="disable"></el-option>
            <el-option label="启用" value="enable"></el-option>
            <el-option label="注销" value="delete"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="batchDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleBatchSubmit">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import api from '../../api/index'
import { getDepartmentTree } from '../../api/department'

// 搜索表单
const searchForm = reactive({
  keyword: '',
  departmentId: undefined
})

// 用户列表
const userList = ref([])

// 分页
const pagination = reactive({
  current: 1,
  size: 10,
  total: 0
})

// 对话框
const dialogVisible = ref(false)
const batchDialogVisible = ref(false)
const dialogType = ref('add')

// 表单
const form = reactive({
  id: undefined,
  name: '',
  employeeId: '',
  email: '',
  phone: '',
  departmentId: undefined,
  departmentName: '',
  status: 'ACTIVE'
})

// 部门树
const departmentTree = ref([])

// 批量操作表单
const batchForm = reactive({
  operation: 'resetPassword',
  ids: []
})

// 表格选择
const multipleSelection = ref([])

// 监听表格选择变化
const handleSelectionChange = (val: any) => {
  multipleSelection.value = val
}

// 获取部门名称
const getDepartmentName = (departmentId: number | undefined) => {
  if (!departmentId) return '无'
  const dept = departmentTree.value.find((item: any) => item.id === departmentId)
  return dept ? dept.name : '未知'
}

// 处理部门选择变化
const handleDepartmentChange = (departmentId: number | null) => {
  if (!departmentId) {
    form.departmentName = ''
    return
  }
  const dept = departmentTree.value.find((item: any) => item.id === departmentId)
  if (dept) {
    form.departmentName = dept.name
  }
}

// 加载部门树
const loadDepartmentTree = async () => {
  try {
    const response = await getDepartmentTree()
    if (response.success) {
      departmentTree.value = response.data || []
    } else {
      ElMessage.error(response.message || '获取部门树失败')
    }
  } catch (error) {
    console.error('获取部门树失败:', error)
    ElMessage.error('获取部门树失败')
  }
}

// 搜索
const handleSearch = () => {
  getUserList()
}

// 获取用户列表
const getUserList = async () => {
  try {
    console.log('获取用户列表前，token:', localStorage.getItem('token'));
    const response = await api.get('/users', {
      params: {
        page: pagination.current,
        size: pagination.size,
        keyword: searchForm.keyword,
        departmentId: searchForm.departmentId
      }
    })
    console.log('获取用户列表后，response:', response);
    if (response.success) {
      userList.value = response.records || []
      pagination.total = response.total || 0
    } else {
      ElMessage.error(response.message || '获取用户列表失败')
    }
  } catch (error) {
    console.error('获取用户列表失败:', error)
    ElMessage.error('获取用户列表失败')
  }
}

// 新增用户
const handleAdd = () => {
  dialogType.value = 'add'
  Object.assign(form, {
    id: undefined,
    name: '',
    employeeId: '',
    email: '',
    phone: '',
    departmentId: null,
    departmentName: '',
    status: 'ACTIVE'
  })
  dialogVisible.value = true
}

// 编辑用户
const handleEdit = (row: any) => {
  dialogType.value = 'edit'
  Object.assign(form, row)
  // 设置部门名称
  if (row.departmentId) {
    const dept = departmentTree.value.find((item: any) => item.id === row.departmentId)
    if (dept) {
      form.departmentName = dept.name
    }
  } else {
    form.departmentName = ''
  }
  dialogVisible.value = true
}

// 提交表单
const handleSubmit = async () => {
  try {
    let response
    if (dialogType.value === 'add') {
      response = await api.post('/users', form)
    } else {
      response = await api.put(`/users/${form.id}`, form)
    }
    if (response.success) {
      ElMessage.success(response.message || '保存成功')
      dialogVisible.value = false
      getUserList()
    } else {
      ElMessage.error(response.message || '保存失败')
    }
  } catch (error) {
    console.error('保存用户失败:', error)
    ElMessage.error('保存失败')
  }
}

// 重置密码
const handleResetPassword = async (id) => {
  try {
    const response = await api.post(`/users/${id}/reset-password`)
    if (response.success) {
      ElMessage.success(response.message || '密码重置成功')
    } else {
      ElMessage.error(response.message || '密码重置失败')
    }
  } catch (error) {
    console.error('重置密码失败:', error)
    ElMessage.error('密码重置失败')
  }
}

// 禁用/启用用户
const handleDisable = async (id, disable) => {
  try {
    let response
    if (disable) {
      response = await api.post(`/users/${id}/disable`)
    } else {
      response = await api.post(`/users/${id}/enable`)
    }
    if (response.success) {
      ElMessage.success(response.message || '操作成功')
      getUserList()
    } else {
      ElMessage.error(response.message || '操作失败')
    }
  } catch (error) {
    console.error('操作失败:', error)
    ElMessage.error('操作失败')
  }
}

// 删除用户
const handleDelete = async (id) => {
  try {
    const response = await api.delete(`/users/${id}`)
    if (response.success) {
      ElMessage.success(response.message || '删除成功')
      getUserList()
    } else {
      ElMessage.error(response.message || '删除失败')
    }
  } catch (error) {
    console.error('删除用户失败:', error)
    ElMessage.error('删除失败')
  }
}

// 批量操作
const handleBatchOperation = () => {
  if (multipleSelection.value.length === 0) {
    ElMessage.warning('请选择要操作的用户')
    return
  }
  batchForm.ids = multipleSelection.value.map(item => item.id)
  batchDialogVisible.value = true
}

// 提交批量操作
const handleBatchSubmit = async () => {
  try {
    const response = await api.post('/users/batch', {
      operation: batchForm.operation,
      ids: batchForm.ids
    })
    if (response.success) {
      ElMessage.success(`${response.message || '批量操作完成'}，成功${response.successCount || 0}个，失败${response.failureCount || 0}个`)
      batchDialogVisible.value = false
      getUserList()
    } else {
      ElMessage.error(response.message || '批量操作失败')
    }
  } catch (error) {
    console.error('批量操作失败:', error)
    ElMessage.error('批量操作失败')
  }
}

// 分页变化
const handleSizeChange = (size) => {
  pagination.size = size
  getUserList()
}

const handleCurrentChange = (current) => {
  pagination.current = current
  getUserList()
}

// 初始化
onMounted(() => {
  getUserList()
  loadDepartmentTree()
})
</script>

<style scoped>
.user-management {
  padding: 20px;
}

.dialog-footer {
  text-align: right;
}
</style>
