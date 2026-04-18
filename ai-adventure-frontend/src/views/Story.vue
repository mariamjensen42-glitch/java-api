<template>
  <div class="story-container">
    <h3>故事</h3>
    <div class="story-content">
      <div class="story-text">
        {{ currentStory }}
      </div>
    </div>
    <div class="action-options">
      <h4>可用动作</h4>
      <div class="options-list">
        <button 
          v-for="(option, index) in actionOptions" 
          :key="index"
          class="option-btn"
          @click="selectAction(option)"
        >
          {{ option }}
        </button>
      </div>
    </div>
    <div class="story-history">
      <h4>故事历史</h4>
      <div class="history-list">
        <div 
          v-for="(history, index) in storyHistory" 
          :key="index"
          class="history-item"
        >
          {{ history }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Story',
  data() {
    return {
      currentStory: '你来到了一个神秘的森林，周围是高大的树木，阳光透过树叶洒下斑驳的光影。前方有一条小路，通向未知的地方。',
      actionOptions: [
        '沿着小路前进',
        '探索森林深处',
        '休息一下',
        '返回原路'
      ],
      storyHistory: [
        '游戏开始：你来到了一个神秘的森林。'
      ]
    }
  },
  methods: {
    selectAction(option) {
      // 这里将实现动作选择逻辑
      // 暂时使用模拟数据
      const newStory = `你选择了：${option}。${this.getRandomStory(option)}`
      this.storyHistory.push(newStory)
      this.currentStory = newStory
      this.generateNewOptions()
    },
    getRandomStory(option) {
      const stories = {
        '沿着小路前进': '你沿着小路走了一段时间，发现了一个古老的石拱门，上面刻着神秘的符文。',
        '探索森林深处': '你深入森林，发现了一个清澈的湖泊，湖边有一座小木屋。',
        '休息一下': '你找了一块干净的石头坐下休息，突然听到附近有奇怪的声音。',
        '返回原路': '你决定返回，在途中遇到了一位神秘的老人，他似乎在寻找什么。'
      }
      return stories[option] || '你继续前进，发现了新的景象。'
    },
    generateNewOptions() {
      // 生成新的动作选项
      this.actionOptions = [
        '继续前进',
        '仔细观察周围',
        '与遇到的人交谈',
        '使用物品'
      ]
    }
  }
}
</script>

<style scoped>
.story-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.story-content {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.story-text {
  font-size: 16px;
  line-height: 1.5;
  color: #333;
}

.action-options {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.options-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 10px;
}

.option-btn {
  padding: 12px;
  background-color: #f0f0f0;
  border: 1px solid #ddd;
  border-radius: 4px;
  text-align: left;
  cursor: pointer;
  transition: background-color 0.2s;
}

.option-btn:hover {
  background-color: #e0e0e0;
}

.story-history {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  max-height: 300px;
  overflow-y: auto;
}

.history-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 10px;
}

.history-item {
  padding: 10px;
  background-color: #f9f9f9;
  border-left: 3px solid #4CAF50;
  font-size: 14px;
  color: #666;
}

@media (max-width: 768px) {
  .story-container {
    gap: 15px;
  }
  
  .story-content,
  .action-options,
  .story-history {
    padding: 15px;
  }
}
</style>