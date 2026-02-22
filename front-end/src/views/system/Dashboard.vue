<template>
  <div class="dashboard">
    <!-- 页面标题 -->
    <div class="dashboard-header">
      <h2 class="dashboard-title">数据概览</h2>
      <p class="dashboard-subtitle">组织架构与权限体系数据统计</p>
    </div>

    <!-- 统计卡片 -->
    <div class="stats-cards">
      <el-card class="stats-card">
        <div class="stats-card-content">
          <div class="stats-info">
            <div class="stats-value">{{ stats.users }}</div>
            <div class="stats-label">总用户数</div>
          </div>
          <div class="stats-icon user-icon">
            <el-icon><User /></el-icon>
          </div>
        </div>
      </el-card>

      <el-card class="stats-card">
        <div class="stats-card-content">
          <div class="stats-info">
            <div class="stats-value">{{ stats.departments }}</div>
            <div class="stats-label">部门数量</div>
          </div>
          <div class="stats-icon dept-icon">
            <el-icon><OfficeBuilding /></el-icon>
          </div>
        </div>
      </el-card>

      <el-card class="stats-card">
        <div class="stats-card-content">
          <div class="stats-info">
            <div class="stats-value">{{ stats.roles }}</div>
            <div class="stats-label">角色数量</div>
          </div>
          <div class="stats-icon role-icon">
            <el-icon><CollectionTag /></el-icon>
          </div>
        </div>
      </el-card>

      <el-card class="stats-card">
        <div class="stats-card-content">
          <div class="stats-info">
            <div class="stats-value">{{ stats.permissions }}</div>
            <div class="stats-label">权限数量</div>
          </div>
          <div class="stats-icon perm-icon">
            <el-icon><Lock /></el-icon>
          </div>
        </div>
      </el-card>
    </div>

    <!-- 图表区域 -->
    <div class="charts-container">
      <!-- 用户增长趋势 -->
      <el-card class="chart-card">
        <template #header>
          <div class="chart-header">
            <span>用户增长趋势</span>
          </div>
        </template>
        <div class="chart-content">
          <canvas ref="userGrowthChart"></canvas>
        </div>
      </el-card>

      <!-- 部门人员分布 -->
      <el-card class="chart-card">
        <template #header>
          <div class="chart-header">
            <span>部门人员分布</span>
          </div>
        </template>
        <div class="chart-content">
          <canvas ref="departmentDistributionChart"></canvas>
        </div>
      </el-card>

      <!-- 角色权限分布 -->
      <el-card class="chart-card">
        <template #header>
          <div class="chart-header">
            <span>角色权限分布</span>
          </div>
        </template>
        <div class="chart-content">
          <canvas ref="rolePermissionChart"></canvas>
        </div>
      </el-card>

      <!-- 系统登录趋势 -->
      <el-card class="chart-card">
        <template #header>
          <div class="chart-header">
            <span>系统登录趋势</span>
          </div>
        </template>
        <div class="chart-content">
          <canvas ref="loginTrendChart"></canvas>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { User, OfficeBuilding, CollectionTag, Lock } from '@element-plus/icons-vue'
import Chart from 'chart.js/auto'

// 统计数据
const stats = ref({
  users: 248,
  departments: 12,
  roles: 8,
  permissions: 42
})

// 图表引用
const userGrowthChart = ref<HTMLCanvasElement | null>(null)
const departmentDistributionChart = ref<HTMLCanvasElement | null>(null)
const rolePermissionChart = ref<HTMLCanvasElement | null>(null)
const loginTrendChart = ref<HTMLCanvasElement | null>(null)

// 图表实例
let userGrowthChartInstance: Chart | null = null
let departmentDistributionChartInstance: Chart | null = null
let rolePermissionChartInstance: Chart | null = null
let loginTrendChartInstance: Chart | null = null

// 初始化图表
onMounted(() => {
  initUserGrowthChart()
  initDepartmentDistributionChart()
  initRolePermissionChart()
  initLoginTrendChart()
})

// 用户增长趋势图表
const initUserGrowthChart = () => {
  if (!userGrowthChart.value) return

  const ctx = userGrowthChart.value.getContext('2d')
  if (!ctx) return

  userGrowthChartInstance = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['1月', '2月', '3月', '4月', '5月', '6月'],
      datasets: [
        {
          label: '新增用户',
          data: [12, 19, 3, 5, 2, 3],
          borderColor: '#667eea',
          backgroundColor: 'rgba(102, 126, 234, 0.1)',
          tension: 0.4,
          fill: true
        },
        {
          label: '活跃用户',
          data: [28, 48, 40, 19, 36, 27],
          borderColor: '#764ba2',
          backgroundColor: 'rgba(118, 75, 162, 0.1)',
          tension: 0.4,
          fill: true
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'top'
        }
      },
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  })
}

// 部门人员分布图表
const initDepartmentDistributionChart = () => {
  if (!departmentDistributionChart.value) return

  const ctx = departmentDistributionChart.value.getContext('2d')
  if (!ctx) return

  departmentDistributionChartInstance = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: ['技术部', '市场部', '销售部', '人力资源部', '财务部', '运营部'],
      datasets: [
        {
          data: [85, 35, 42, 18, 12, 25],
          backgroundColor: [
            '#667eea',
            '#764ba2',
            '#f093fb',
            '#4facfe',
            '#43e97b',
            '#fa709a'
          ],
          borderWidth: 0
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'right'
        }
      }
    }
  })
}

// 角色权限分布图表
const initRolePermissionChart = () => {
  if (!rolePermissionChart.value) return

  const ctx = rolePermissionChart.value.getContext('2d')
  if (!ctx) return

  rolePermissionChartInstance = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['超级管理员', '系统管理员', '部门主管', '普通员工', '访客'],
      datasets: [
        {
          label: '权限数量',
          data: [42, 35, 28, 15, 5],
          backgroundColor: [
            'rgba(102, 126, 234, 0.8)',
            'rgba(118, 75, 162, 0.8)',
            'rgba(240, 147, 251, 0.8)',
            'rgba(79, 172, 254, 0.8)',
            'rgba(67, 233, 123, 0.8)'
          ],
          borderWidth: 0,
          borderRadius: 8
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          display: false
        }
      },
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  })
}

// 系统登录趋势图表
const initLoginTrendChart = () => {
  if (!loginTrendChart.value) return

  const ctx = loginTrendChart.value.getContext('2d')
  if (!ctx) return

  loginTrendChartInstance = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
      datasets: [
        {
          label: '登录次数',
          data: [65, 59, 80, 81, 56, 55, 40],
          borderColor: '#4facfe',
          backgroundColor: 'rgba(79, 172, 254, 0.1)',
          tension: 0.4,
          fill: true
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'top'
        }
      },
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  })
}
</script>

<style scoped>
/* 仪表盘容器 */
.dashboard {
  padding: 24px;
  height: 100%;
  overflow-y: auto;
}

/* 仪表盘头部 */
.dashboard-header {
  margin-bottom: 32px;
}

.dashboard-title {
  font-size: 28px;
  font-weight: 700;
  color: #333;
  margin-bottom: 8px;
}

.dashboard-subtitle {
  font-size: 16px;
  color: #666;
  margin: 0;
}

/* 统计卡片 */
.stats-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 24px;
  margin-bottom: 32px;
}

.stats-card {
  border-radius: 16px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  overflow: hidden;
}

.stats-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
}

.stats-card-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 24px;
}

.stats-info {
  flex: 1;
}

.stats-value {
  font-size: 32px;
  font-weight: 700;
  color: #333;
  margin-bottom: 4px;
}

.stats-label {
  font-size: 14px;
  color: #666;
}

.stats-icon {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
}

.user-icon {
  background: linear-gradient(135deg, #667eea, #764ba2);
  color: #fff;
}

.dept-icon {
  background: linear-gradient(135deg, #f093fb, #f5576c);
  color: #fff;
}

.role-icon {
  background: linear-gradient(135deg, #4facfe, #00f2fe);
  color: #fff;
}

.perm-icon {
  background: linear-gradient(135deg, #43e97b, #38f9d7);
  color: #fff;
}

/* 图表容器 */
.charts-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(480px, 1fr));
  gap: 24px;
}

.chart-card {
  border-radius: 16px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
  overflow: hidden;
}

.chart-header {
  font-size: 16px;
  font-weight: 600;
  color: #333;
}

.chart-content {
  height: 300px;
  padding: 16px;
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .charts-container {
    grid-template-columns: 1fr;
  }
  
  .stats-cards {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .dashboard {
    padding: 16px;
  }
  
  .dashboard-title {
    font-size: 24px;
  }
  
  .stats-cards {
    grid-template-columns: 1fr;
  }
  
  .chart-content {
    height: 250px;
  }
}
</style>