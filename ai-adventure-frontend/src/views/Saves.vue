<template>
  <div class="saves-container">
    <h3>存档</h3>
    <button class="create-save-btn" @click="createSave">创建新存档</button>
    <div class="saves-list">
      <div 
        v-for="save in saves" 
        :key="save.id"
        class="save-card"
      >
        <div class="save-header">
          <h4>{{ save.saveName }}</h4>
          <span class="slot-number">槽位 {{ save.slotNumber }}</span>
        </div>
        <div class="save-info">
          <p>存档时间: {{ save.saveTime }}</p>
          <p>游戏状态: {{ save.gameState }}</p>
        </div>
        <div class="save-actions">
          <button class="action-btn load" @click="loadSave(save.id)">加载</button>
          <button class="action-btn delete" @click="deleteSave(save.id)">删除</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Saves',
  data() {
    return {
      saves: [
        {
          id: 1,
          slotNumber: 1,
          saveName: '冒险开始',
          saveTime: '2024-01-01 12:00:00',
          gameState: '森林入口'
        },
        {
          id: 2,
          slotNumber: 2,
          saveName: '找到神秘拱门',
          saveTime: '2024-01-02 14:30:00',
          gameState: '古老的石拱门'
        },
        {
          id: 3,
          slotNumber: 3,
          saveName: '击败森林巨魔',
          saveTime: '2024-01-03 09:15:00',
          gameState: '巨魔洞穴'
        }
      ]
    }
  },
  methods: {
    createSave() {
      // 这里将实现创建存档逻辑
      const newSave = {
        id: this.saves.length + 1,
        slotNumber: this.saves.length + 1,
        saveName: `存档 ${this.saves.length + 1}`,
        saveTime: new Date().toLocaleString(),
        gameState: '当前位置'
      }
      this.saves.push(newSave)
      alert('存档创建成功！')
    },
    loadSave(saveId) {
      // 这里将实现加载存档逻辑
      alert('加载存档中...')
    },
    deleteSave(saveId) {
      // 这里将实现删除存档逻辑
      if (confirm('确定要删除这个存档吗？')) {
        this.saves = this.saves.filter(save => save.id !== saveId)
        alert('存档已删除')
      }
    }
  }
}
</script>

<style scoped>
.saves-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.create-save-btn {
  align-self: flex-start;
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.create-save-btn:hover {
  background-color: #45a049;
}

.saves-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.save-card {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 1px solid #ddd;
  transition: transform 0.2s;
}

.save-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.save-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
  padding-bottom: 10px;
  border-bottom: 1px solid #ddd;
}

.save-header h4 {
  color: #333;
  margin: 0;
}

.slot-number {
  padding: 4px 8px;
  background-color: #f0f0f0;
  border-radius: 12px;
  font-size: 12px;
  font-weight: bold;
  color: #666;
}

.save-info {
  margin-bottom: 15px;
}

.save-info p {
  margin: 5px 0;
  color: #666;
  font-size: 14px;
}

.save-actions {
  display: flex;
  gap: 10px;
}

.action-btn {
  flex: 1;
  padding: 8px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.action-btn.load {
  background-color: #2196F3;
  color: white;
}

.action-btn.delete {
  background-color: #f44336;
  color: white;
}

.action-btn:hover {
  opacity: 0.8;
}

@media (max-width: 768px) {
  .saves-list {
    grid-template-columns: 1fr;
  }
  
  .save-card {
    padding: 15px;
  }
}
</style>