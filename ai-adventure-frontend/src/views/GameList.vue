<template>
  <div class="game-list-container">
    <h2>我的游戏</h2>
    <button class="create-game-btn" @click="createGame">创建新游戏</button>
    <div class="game-cards">
      <div 
        v-for="game in games" 
        :key="game.id" 
        class="game-card"
        @click="loadGame(game.id)"
      >
        <h3>{{ game.title }}</h3>
        <p>创建时间: {{ game.createdAt }}</p>
        <p>状态: {{ game.currentState }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GameList',
  data() {
    return {
      games: [
        {
          id: 1,
          title: '奇幻冒险',
          createdAt: '2024-01-01',
          currentState: '进行中'
        },
        {
          id: 2,
          title: '星际探索',
          createdAt: '2024-01-02',
          currentState: '已暂停'
        }
      ]
    }
  },
  methods: {
    createGame() {
      // 这里将实现创建游戏逻辑
      const newGameId = this.games.length + 1
      this.$router.push(`/game/${newGameId}`)
    },
    loadGame(gameId) {
      this.$router.push(`/game/${gameId}`)
    }
  }
}
</script>

<style scoped>
.game-list-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.create-game-btn {
  display: block;
  margin: 0 auto 30px;
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
}

.create-game-btn:hover {
  background-color: #45a049;
}

.game-cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.game-card {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: transform 0.2s;
}

.game-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.game-card h3 {
  margin-bottom: 10px;
  color: #333;
}

.game-card p {
  margin: 5px 0;
  color: #666;
  font-size: 14px;
}
</style>