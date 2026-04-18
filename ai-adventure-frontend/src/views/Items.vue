<template>
  <div class="items-container">
    <h3>物品</h3>
    <div class="items-list">
      <div 
        v-for="item in items" 
        :key="item.id"
        class="item-card"
        :class="{ equipped: item.equipped }"
      >
        <div class="item-header">
          <h4>{{ item.name }}</h4>
          <span v-if="item.equipped" class="equipped-badge">已装备</span>
        </div>
        <div class="item-description">
          {{ item.description }}
        </div>
        <div class="item-bonuses">
          <div v-if="item.attackBonus > 0" class="bonus attack">攻击 +{{ item.attackBonus }}</div>
          <div v-if="item.defenseBonus > 0" class="bonus defense">防御 +{{ item.defenseBonus }}</div>
          <div v-if="item.healthBonus > 0" class="bonus health">生命 +{{ item.healthBonus }}</div>
        </div>
        <div class="item-actions">
          <button class="action-btn" @click="useItem(item)">使用</button>
          <button class="action-btn" @click="toggleEquip(item)">
            {{ item.equipped ? '卸下' : '装备' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Items',
  data() {
    return {
      items: [
        {
          id: 1,
          name: '铁剑',
          description: '一把锋利的铁剑，适合初学者使用。',
          type: 'weapon',
          attackBonus: 5,
          defenseBonus: 0,
          healthBonus: 0,
          equipped: true
        },
        {
          id: 2,
          name: '皮甲',
          description: '用兽皮制作的护甲，提供基本的防护。',
          type: 'armor',
          attackBonus: 0,
          defenseBonus: 3,
          healthBonus: 0,
          equipped: true
        },
        {
          id: 3,
          name: '治疗药水',
          description: '恢复20点生命值的药水。',
          type: 'potion',
          attackBonus: 0,
          defenseBonus: 0,
          healthBonus: 20,
          equipped: false
        },
        {
          id: 4,
          name: '魔法卷轴',
          description: '包含一个初级魔法的卷轴。',
          type: 'scroll',
          attackBonus: 0,
          defenseBonus: 0,
          healthBonus: 0,
          equipped: false
        }
      ]
    }
  },
  methods: {
    useItem(item) {
      // 这里将实现使用物品逻辑
      if (item.type === 'potion' && item.healthBonus > 0) {
        alert(`使用了${item.name}，恢复了${item.healthBonus}点生命值。`);
        // 实际应用中，这里会调用API更新角色生命值
      } else {
        alert(`使用了${item.name}。`);
      }
    },
    toggleEquip(item) {
      item.equipped = !item.equipped;
      // 实际应用中，这里会调用API更新物品装备状态
    }
  }
}
</script>

<style scoped>
.items-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.items-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
}

.item-card {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 1px solid #ddd;
  transition: transform 0.2s;
}

.item-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.item-card.equipped {
  border-color: #4CAF50;
  background-color: #f9fff9;
}

.item-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.item-header h4 {
  color: #333;
  margin: 0;
}

.equipped-badge {
  padding: 4px 8px;
  background-color: #4CAF50;
  color: white;
  border-radius: 12px;
  font-size: 12px;
  font-weight: bold;
}

.item-description {
  margin-bottom: 15px;
  color: #666;
  font-size: 14px;
  line-height: 1.4;
}

.item-bonuses {
  display: flex;
  flex-direction: column;
  gap: 5px;
  margin-bottom: 15px;
}

.bonus {
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: bold;
}

.bonus.attack {
  background-color: #ffebee;
  color: #c62828;
}

.bonus.defense {
  background-color: #e3f2fd;
  color: #1565c0;
}

.bonus.health {
  background-color: #e8f5e8;
  color: #2e7d32;
}

.item-actions {
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

.action-btn:first-child {
  background-color: #4CAF50;
  color: white;
}

.action-btn:last-child {
  background-color: #2196F3;
  color: white;
}

.action-btn:hover {
  opacity: 0.8;
}

@media (max-width: 768px) {
  .items-list {
    grid-template-columns: 1fr;
  }
  
  .item-card {
    padding: 15px;
  }
}
</style>