<template>
  <div class="quests-container">
    <h3>任务</h3>
    <div class="quests-list">
      <div 
        v-for="quest in quests" 
        :key="quest.id"
        class="quest-card"
        :class="quest.status"
      >
        <div class="quest-header">
          <h4>{{ quest.title }}</h4>
          <span class="quest-status">{{ quest.statusText }}</span>
        </div>
        <div class="quest-description">
          {{ quest.description }}
        </div>
        <div class="quest-progress">
          <div class="progress-bar">
            <div class="progress-fill" :style="{ width: (quest.progress / quest.targetProgress) * 100 + '%' }"></div>
          </div>
          <span>{{ quest.progress }}/{{ quest.targetProgress }}</span>
        </div>
        <div class="quest-reward">
          <strong>奖励:</strong> {{ quest.reward }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Quests',
  data() {
    return {
      quests: [
        {
          id: 1,
          title: '探索神秘森林',
          description: '深入森林深处，寻找传说中的神秘宝藏。',
          status: 'in-progress',
          statusText: '进行中',
          progress: 3,
          targetProgress: 5,
          reward: '经验值 +100, 金币 +50'
        },
        {
          id: 2,
          title: '消灭森林巨魔',
          description: '杀死危害村庄的森林巨魔。',
          status: 'pending',
          statusText: '待开始',
          progress: 0,
          targetProgress: 1,
          reward: '经验值 +150, 装备 +1'
        },
        {
          id: 3,
          title: '收集草药',
          description: '收集10株 healing草药。',
          status: 'completed',
          statusText: '已完成',
          progress: 10,
          targetProgress: 10,
          reward: '经验值 +50, 药水 +2'
        }
      ]
    }
  }
}
</script>

<style scoped>
.quests-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.quests-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.quest-card {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border-left: 4px solid #ddd;
}

.quest-card.pending {
  border-left-color: #FF9800;
}

.quest-card.in-progress {
  border-left-color: #2196F3;
}

.quest-card.completed {
  border-left-color: #4CAF50;
}

.quest-card.failed {
  border-left-color: #f44336;
}

.quest-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.quest-header h4 {
  color: #333;
  margin: 0;
}

.quest-status {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 12px;
  font-weight: bold;
}

.quest-card.pending .quest-status {
  background-color: #FFF3E0;
  color: #E65100;
}

.quest-card.in-progress .quest-status {
  background-color: #E3F2FD;
  color: #1565C0;
}

.quest-card.completed .quest-status {
  background-color: #E8F5E8;
  color: #2E7D32;
}

.quest-card.failed .quest-status {
  background-color: #FFEBEE;
  color: #C62828;
}

.quest-description {
  margin-bottom: 15px;
  color: #666;
  font-size: 14px;
  line-height: 1.4;
}

.quest-progress {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 10px;
}

.progress-bar {
  flex: 1;
  height: 10px;
  background-color: #f0f0f0;
  border-radius: 5px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background-color: #4CAF50;
  border-radius: 5px;
  transition: width 0.3s ease;
}

.quest-reward {
  font-size: 14px;
  color: #666;
}

@media (max-width: 768px) {
  .quest-card {
    padding: 15px;
  }
}
</style>