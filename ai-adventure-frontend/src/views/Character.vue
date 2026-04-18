<template>
  <div class="character-container">
    <h3>角色信息</h3>
    <div class="character-card">
      <div class="character-header">
        <h4>{{ character.name }}</h4>
      </div>
      <div class="character-attributes">
        <div class="attribute">
          <label>健康</label>
          <div class="attribute-bar">
            <div class="attribute-fill" :style="{ width: (character.health / character.maxHealth) * 100 + '%' }"></div>
            <span>{{ character.health }}/{{ character.maxHealth }}</span>
          </div>
        </div>
        <div class="attribute">
          <label>攻击</label>
          <div class="attribute-bar">
            <div class="attribute-fill attack" :style="{ width: (character.attack / 100) * 100 + '%' }"></div>
            <span>{{ character.attack }}</span>
          </div>
        </div>
        <div class="attribute">
          <label>防御</label>
          <div class="attribute-bar">
            <div class="attribute-fill defense" :style="{ width: (character.defense / 100) * 100 + '%' }"></div>
            <span>{{ character.defense }}</span>
          </div>
        </div>
        <div class="attribute">
          <label>道德</label>
          <div class="attribute-bar">
            <div class="attribute-fill morality" :style="{ width: (character.morality / 100) * 100 + '%' }"></div>
            <span>{{ character.morality }}</span>
          </div>
        </div>
        <div class="attribute">
          <label>知识</label>
          <div class="attribute-bar">
            <div class="attribute-fill knowledge" :style="{ width: (character.knowledge / 100) * 100 + '%' }"></div>
            <span>{{ character.knowledge }}</span>
          </div>
        </div>
      </div>
      <div class="character-skills">
        <h5>技能</h5>
        <p>{{ character.skills }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Character',
  data() {
    return {
      character: {
        name: '冒险英雄',
        health: 85,
        maxHealth: 100,
        attack: 15,
        defense: 8,
        morality: 60,
        knowledge: 45,
        skills: '剑术, 魔法基础, 生存技能'
      }
    }
  }
}
</script>

<style scoped>
.character-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.character-card {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.character-header {
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #ddd;
}

.character-header h4 {
  color: #333;
  margin: 0;
}

.character-attributes {
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-bottom: 20px;
}

.attribute {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.attribute label {
  font-weight: bold;
  color: #555;
  font-size: 14px;
}

.attribute-bar {
  position: relative;
  height: 20px;
  background-color: #f0f0f0;
  border-radius: 10px;
  overflow: hidden;
}

.attribute-fill {
  height: 100%;
  border-radius: 10px;
  transition: width 0.3s ease;
}

.attribute-fill.attack {
  background-color: #f44336;
}

.attribute-fill.defense {
  background-color: #2196F3;
}

.attribute-fill.morality {
  background-color: #4CAF50;
}

.attribute-fill.knowledge {
  background-color: #FF9800;
}

.attribute-bar span {
  position: absolute;
  top: 0;
  left: 10px;
  line-height: 20px;
  font-size: 12px;
  font-weight: bold;
  color: #333;
}

.character-skills {
  padding-top: 20px;
  border-top: 1px solid #ddd;
}

.character-skills h5 {
  margin-bottom: 10px;
  color: #333;
}

.character-skills p {
  color: #666;
  margin: 0;
}

@media (max-width: 768px) {
  .character-card {
    padding: 15px;
  }
}
</style>