<template>
  <div class="system-management">
    <el-container style="height: 100vh;">
      <!-- 左侧菜单 -->
      <el-aside width="240px" class="system-sidebar">
        <div class="sidebar-header">
          <h3 class="sidebar-title">组织架构管理</h3>
        </div>
        <el-menu
          :default-active="activeMenu"
          class="system-menu"
          router
          background-color="transparent"
          text-color="#fff"
          active-text-color="#fff"
        >
          <el-menu-item index="/system" class="menu-item">
            <el-icon class="menu-item-icon"><Grid /></el-icon>
            <span>首页</span>
          </el-menu-item>
          <el-sub-menu index="system" class="menu-sub">
            <template #title>
              <el-icon class="menu-icon"><Setting /></el-icon>
              <span>系统管理</span>
            </template>
            <el-menu-item index="/system/user" class="menu-item">
              <el-icon class="menu-item-icon"><User /></el-icon>
              <span>用户管理</span>
            </el-menu-item>
            <el-menu-item index="/system/department" class="menu-item">
              <el-icon class="menu-item-icon"><OfficeBuilding /></el-icon>
              <span>部门管理</span>
            </el-menu-item>
            <el-menu-item index="/system/role" class="menu-item">
              <el-icon class="menu-item-icon"><CollectionTag /></el-icon>
              <span>角色管理</span>
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
        <div class="sidebar-footer">
          <div class="user-info" @click="handleUserInfo">
            <el-avatar class="user-avatar" :size="40">
              {{ userInitial }}
            </el-avatar>
            <div class="user-details">
              <div class="user-name">{{ userName }}</div>
              <div class="user-role">系统管理员</div>
            </div>
          </div>
          <el-button type="primary" class="logout-button" @click="handleLogout">
            <el-icon><SwitchButton /></el-icon>
            <span>退出登录</span>
          </el-button>
        </div>
      </el-aside>

      <!-- 右侧内容 -->
      <el-container class="system-content">
        <el-header class="system-header">
          <div class="header-title">
            <el-icon class="header-icon"><Grid /></el-icon>
            <span>{{ pageTitle }}</span>
          </div>
          <div class="header-actions">
            <el-button type="default" class="header-button">
              <el-icon><Refresh /></el-icon>
              <span>刷新</span>
            </el-button>
            <el-dropdown>
              <el-button type="default" class="header-button">
                <el-icon><Bell /></el-icon>
                <span>通知</span>
                <el-badge :value="3" class="notification-badge" />
              </el-button>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>通知1</el-dropdown-item>
                  <el-dropdown-item>通知2</el-dropdown-item>
                  <el-dropdown-item>通知3</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>
        <el-main class="system-main">
          <router-view v-slot="{ Component }">
            <transition name="fade" mode="out-in">
              <component :is="Component" />
            </transition>
          </router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElIcon } from 'element-plus'
import {
  Setting,
  User,
  OfficeBuilding,
  CollectionTag,
  Grid,
  Refresh,
  Bell,
  SwitchButton
} from '@element-plus/icons-vue'

const route = useRoute()
const router = useRouter()

// 计算当前激活的菜单
const activeMenu = computed(() => {
  const path = route.path
  if (path === '/system') return '/system'
  return path.includes('/system') ? 'system' : ''
})

// 计算页面标题
const pageTitle = computed(() => {
  const path = route.path
  if (path === '/system') return '数据概览'
  if (path.includes('/system/user')) return '用户管理'
  if (path.includes('/system/department')) return '部门管理'
  if (path.includes('/system/role')) return '角色管理'
  return '系统管理'
})

// 用户信息
const userName = ref('管理员')
const userInitial = computed(() => {
  return userName.value.charAt(0).toUpperCase()
})

// 处理用户信息点击
const handleUserInfo = () => {
  // 可以打开用户信息弹窗
  ElMessage.info('用户信息功能开发中')
}

// 处理退出登录
const handleLogout = () => {
  // 清除本地存储的token和用户信息
  localStorage.removeItem('token')
  localStorage.removeItem('user')
  // 跳转到登录页面
  router.push('/login')
  ElMessage.success('退出登录成功')
}
</script>

<style scoped>
/* 系统管理容器 */
.system-management {
  width: 100%;
  height: 100vh;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* 侧边栏 */
.system-sidebar {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 0 24px 24px 0;
  box-shadow: 4px 0 20px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

/* 侧边栏头部 */
.sidebar-header {
  padding: 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.sidebar-title {
  color: #fff;
  font-size: 20px;
  font-weight: 700;
  margin: 0;
  text-align: center;
}

/* 系统菜单 */
.system-menu {
  flex: 1;
  padding: 16px 0;
}

.menu-sub {
  margin: 8px 16px;
  border-radius: 12px;
  overflow: hidden;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
}

.menu-icon {
  font-size: 20px;
  margin-right: 12px;
}

.menu-item {
  transition: all 0.3s ease;
  margin: 4px 12px;
  border-radius: 8px;
}

.menu-item:hover {
  background: rgba(255, 255, 255, 0.2);
  transform: translateX(4px);
}

.menu-item-icon {
  font-size: 16px;
  margin-right: 8px;
}

/* 侧边栏底部 */
.sidebar-footer {
  padding: 24px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

/* 用户信息 */
.user-info {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding: 16px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.user-info:hover {
  background: rgba(255, 255, 255, 0.2);
  transform: translateY(-2px);
}

.user-avatar {
  background: rgba(255, 255, 255, 0.3);
  margin-right: 12px;
}

.user-details {
  flex: 1;
}

.user-name {
  color: #fff;
  font-weight: 600;
  margin-bottom: 4px;
}

.user-role {
  color: rgba(255, 255, 255, 0.8);
  font-size: 12px;
}

/* 退出登录按钮 */
.logout-button {
  width: 100%;
  border-radius: 12px;
  background: rgba(255, 255, 255, 0.2);
  border: 1px solid rgba(255, 255, 255, 0.3);
  color: #fff;
  transition: all 0.3s ease;
}

.logout-button:hover {
  background: rgba(255, 255, 255, 0.3);
  transform: translateY(-2px);
}

/* 系统内容 */
.system-content {
  background: #f8f9fa;
}

/* 系统头部 */
.system-header {
  background: #fff;
  border-bottom: 1px solid #e9ecef;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 32px;
  height: 72px;
}

.header-title {
  display: flex;
  align-items: center;
}

.header-icon {
  font-size: 24px;
  color: #667eea;
  margin-right: 12px;
}

.header-title span {
  font-size: 18px;
  font-weight: 600;
  color: #333;
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.header-button {
  border-radius: 8px;
  transition: all 0.3s ease;
}

.header-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.notification-badge {
  position: absolute;
  top: -4px;
  right: -4px;
}

/* 系统主内容 */
.system-main {
  padding: 32px;
  background: #f8f9fa;
}

/* 页面标题 */
.page-title {
  margin-bottom: 24px;
  font-size: 24px;
  font-weight: 700;
  color: #333;
}

/* 淡入淡出动画 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .system-sidebar {
    width: 200px;
  }
  
  .sidebar-header {
    padding: 20px;
  }
  
  .sidebar-title {
    font-size: 18px;
  }
  
  .system-main {
    padding: 24px;
  }
}

@media (max-width: 768px) {
  .system-sidebar {
    width: 180px;
  }
  
  .sidebar-footer {
    padding: 20px;
  }
  
  .system-header {
    padding: 0 20px;
  }
  
  .header-title span {
    font-size: 16px;
  }
}
</style>