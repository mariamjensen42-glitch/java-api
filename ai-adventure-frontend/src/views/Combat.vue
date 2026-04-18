<template>
  <div class="combat-container">
    <h3>战斗</h3>
    <div class="combat-arena">
      <div class="combatant player">
        <h4>{{ player.name }}</h4>
        <div class="health-bar">
          <div class="health-fill" :style="{ width: (player.health / player.maxHealth) * 100 + '%' }"></div>
          <span>{{ player.health }}/{{ player.maxHealth }}</span>
        </div>
        <div class="stats">
          <p>攻击: {{ player.attack }}</p>
          <p>防御: {{ player.defense }}</p>
        </div>
      </div>
      <div class="combat-separator">VS</div>
      <div class="combatant enemy">
        <h4>{{ enemy.name }}</h4>
        <div class="health-bar">
          <div class="health-fill enemy" :style="{ width: (enemy.health / enemy.maxHealth) * 100 + '%' }"></div>
          <span>{{ enemy.health }}/{{ enemy.maxHealth }}</span>
        </div>
        <div class="stats">
          <p>攻击: {{ enemy.attack }}</p>
          <p>防御: {{ enemy.defense }}</p>
        </div>
      </div>
    </div>
    <div class="combat-log">
      <h4>战斗日志</h4>
      <div class="log-content">
        <div v-for="(log, index) in combatLog" :key="index" class="log-item">
          {{ log }}
        </div>
      </div>
    </div>
    <div class="combat-actions">
      <h4>战斗动作</h4>
      <div class="action-buttons">
        <button class="action-btn" @click="attack">攻击</button>
        <button class="action-btn" @click="defend">防御</button>
        <button class="action-btn" @click="useItem">使用物品</button>
        <button class="action-btn" @click="flee">逃跑</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Combat',
  data() {
    return {
      player: {
        name: '冒险英雄',
        health: 85,
        maxHealth: 100,
        attack: 15,
        defense: 8
      },
      enemy: {
        name: '森林巨魔',
        health: 70,
        maxHealth: 70,
        attack: 12,
        defense: 5
      },
      combatLog: [
        '战斗开始！你遇到了一个森林巨魔。'
      ]
    }
  },
  methods: {
    attack() {
      const damage = Math.max(1, this.player.attack - this.enemy.defense)
      this.enemy.health = Math.max(0, this.enemy.health - damage)
      this.combatLog.push(`你对${this.enemy.name}造成了${damage}点伤害。`)
      
      if (this.enemy.health <= 0) {
        this.combatLog.push(`你击败了${this.enemy.name}！`)
        return
      }
      
      this.enemyAttack()
    },
    defend() {
      this.combatLog.push('你采取了防御姿态。')
      this.enemyAttack(true)
    },
    useItem() {
      // 这里将实现使用物品逻辑
      this.combatLog.push('你使用了一个治疗药水，恢复了20点生命值。')
      this.player.health = Math.min(this.player.maxHealth, this.player.health + 20)
      this.enemyAttack()
    },
    flee() {
      // 这里将实现逃跑逻辑
      this.combatLog.push('你成功逃跑了！')
    },
    enemyAttack(defending = false) {
      const defense = defending ? this.player.defense * 1.5 : this.player.defense
      const damage = Math.max(1, this.enemy.attack - defense)
      this.player.health = Math.max(0, this.player.health - damage)
      this.combatLog.push(`${this.enemy.name}对你造成了${damage}点伤害。`)
      
      if (this.player.health <= 0) {
        this.combatLog.push('你被击败了！')
      }
    }
  }
}
</script>

<style scoped>
.combat-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.combat-arena {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.combatant {
  flex: 1;
  padding: 15px;
  border-radius: 8px;
}

.combatant.player {
  background-color: #e3f2fd;
}

.combatant.enemy {
  background-color: #ffebee;
}

.combatant h4 {
  text-align: center;
  margin-bottom: 10px;
  color: #333;
}

.health-bar {
  position: relative;
  height: 20px;
  background-color: #f0f0f0;
  border-radius: 10px;
  overflow: hidden;
  margin-bottom: 10px;
}

.health-fill {
  height: 100%;
  background-color: #4CAF50;
  border-radius: 10px;
  transition: width 0.3s ease;
}

.health-fill.enemy {
  background-color: #f44336;
}

.health-bar span {
  position: absolute;
  top: 0;
  left: 10px;
  line-height: 20px;
  font-size: 12px;
  font-weight: bold;
  color: #333;
}

.stats {
  display: flex;
  justify-content: space-around;
  font-size: 14px;
  color: #666;
}

.combat-separator {
  font-size: 24px;
  font-weight: bold;
  margin: 0 20px;
  color: #333;
}

.combat-log {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  max-height: 200px;
  overflow-y: auto;
}

.log-content {
  display: flex;
  flex-direction: column;
  gap: 5px;
  margin-top: 10px;
}

.log-item {
  padding: 5px 10px;
  background-color: #f9f9f9;
  border-radius: 4px;
  font-size: 14px;
  color: #333;
}

.combat-actions {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.action-buttons {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
  margin-top: 10px;
}

.action-btn {
  padding: 10px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.action-btn:nth-child(1) {
  background-color: #f44336;
  color: white;
}

.action-btn:nth-child(2) {
  background-color: #2196F3;
  color: white;
}

.action-btn:nth-child(3) {
  background-color: #4CAF50;
  color: white;
}

.action-btn:nth-child(4) {
  background-color: #FF9800;
  color: white;
}

.action-btn:hover {
  opacity: 0.8;
}

@media (max-width: 768px) {
  .combat-arena {
    flex-direction: column;
    gap: 20px;
  }
  
  .combat-separator {
    transform: rotate(90deg);
    margin: 10px 0;
  }
  
  .action-buttons {
    grid-template-columns: 1fr;
  }
}
</style>