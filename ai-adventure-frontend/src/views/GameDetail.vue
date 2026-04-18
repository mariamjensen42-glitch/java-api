<template>
  <div class="game-detail-container">
    <div class="game-header">
      <h2>{{ gameTitle }}</h2>
      <button class="logout-btn" @click="logout">退出登录</button>
    </div>
    <div class="game-nav">
      <router-link to="story" class="nav-item">故事</router-link>
      <router-link to="character" class="nav-item">角色</router-link>
      <router-link to="combat" class="nav-item">战斗</router-link>
      <router-link to="quests" class="nav-item">任务</router-link>
      <router-link to="items" class="nav-item">物品</router-link>
      <router-link to="saves" class="nav-item">存档</router-link>
    </div>
    <div class="game-content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GameDetail',
  data() {
    return {
      gameTitle: '游戏详情'
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('token')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.game-detail-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.game-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #ddd;
}

.game-header h2 {
  color: #333;
  margin: 0;
}

.logout-btn {
  padding: 8px 16px;
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.logout-btn:hover {
  background-color: #d32f2f;
}

.game-nav {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
  flex-wrap: wrap;
}

.nav-item {
  padding: 10px 15px;
  background-color: #f0f0f0;
  color: #333;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.nav-item:hover {
  background-color: #e0e0e0;
}

.nav-item.router-link-active {
  background-color: #4CAF50;
  color: white;
}

.game-content {
  min-height: 500px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
}

@media (max-width: 768px) {
  .game-nav {
    flex-direction: column;
  }
  
  .nav-item {
    text-align: center;
  }
}
</style>