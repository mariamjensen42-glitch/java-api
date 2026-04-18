# AI Adventure API - 完整代码文档

## 目录
1. [项目概述](#项目概述)
2. [技术栈](#技术栈)
3. [项目架构](#项目架构)
4. [核心模块](#核心模块)
5. [数据库模型](#数据库模型)
6. [API 端点](#api-端点)
7. [关键服务](#关键服务)
8. [配置说明](#配置说明)
9. [运行方式](#运行方式)

---

## 项目概述

**AI Adventure API** 是一个基于 Spring Boot 的后端服务，为 AI 驱动的文本冒险游戏提供支持。该项目利用 DeepSeek AI 模型来动态生成故事内容、对话和游戏情节，为玩家提供沉浸式的冒险体验。

### 核心功能
- 用户管理与认证
- 游戏会话管理
- 动态故事生成（基于 DeepSeek AI）
- 角色管理与属性系统
- 战斗系统
- 任务系统
- 物品背包系统
- 存档系统
- 游戏事件记录

---

## 技术栈

### 后端框架
- **Spring Boot 3.2.0** - 主框架
- **Spring Data JPA** - 数据持久化
- **Spring Security** - 安全认证
- **Spring WebFlux** - 响应式 Web 客户端
- **Spring Validation** - 数据验证

### 数据库
- **PostgreSQL** - 关系型数据库

### 工具与库
- **Lombok** - 减少样板代码
- **SpringDoc OpenAPI 2.0.0** - API 文档生成
- **Maven** - 项目构建工具

### Java 版本
- **Java 17**

---

## 项目架构

### 分层架构
项目采用经典的 Spring Boot 分层架构：

```
ai-adventure-api/
├── config/          # 配置层（安全配置等）
├── controller/      # 控制器层（REST API 端点）
├── dto/             # 数据传输对象（API 请求/响应）
├── model/           # 实体模型（数据库映射）
├── repository/      # 数据访问层（JPA Repository）
├── service/         # 业务逻辑层（服务接口与实现）
└── AiAdventureApiApplication.java  # 主应用入口
```

### 架构特点
- **RESTful API** - 采用 REST 架构风格
- **依赖注入** - 使用 Spring 的 IoC 容器
- **事务管理** - Spring Data JPA 自动管理
- **安全认证** - HTTP Basic 认证 + CSRF 禁用
- **API 文档** - Swagger UI 自动生成

---

## 核心模块

### 1. 用户模块 (User Module)
- 用户注册与管理
- 用户认证
- 游戏关联

### 2. 游戏模块 (Game Module)
- 游戏会话创建与管理
- 游戏状态跟踪
- 玩家动作处理

### 3. 故事模块 (Story Module)
- 故事节点管理
- 动态故事生成
- 故事历史记录
- 动作选项生成

### 4. 角色模块 (Player Character Module)
- 角色创建与属性管理
- 健康、攻击、防御属性
- 道德与知识属性
- 技能系统

### 5. 战斗模块 (Combat Module)
- 战斗状态管理
- 敌人属性
- 战斗日志
- 回合制战斗

### 6. 任务模块 (Quest Module)
- 任务创建与跟踪
- 任务进度管理
- 奖励系统

### 7. 物品模块 (Item Module)
- 物品管理
- 装备系统
- 属性加成

### 8. 存档模块 (Save Game Module)
- 游戏存档
- 存档槽管理
- 状态序列化

### 9. 事件模块 (Game Event Module)
- 游戏事件记录
- 时间戳追踪

---

## 数据库模型

### 实体关系图 (ERD)

```
User (用户)
  ├── 1:N → Game (游戏)
        ├── 1:N → GameEvent (游戏事件)
        ├── 1:N → PlayerCharacter (玩家角色)
        │     ├── 1:N → Item (物品)
        │     └── 1:N → Quest (任务)
        ├── 1:N → SaveGame (存档)
        ├── 1:N → StoryNode (故事节点)
        │     └── 1:N → StoryNode (子节点，自关联)
        └── 1:N → Combat (战斗)
```

### 实体详细说明

#### 1. User
**表名**: `users`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 用户ID |
| username | String | NOT NULL, UNIQUE | 用户名 |
| password | String | NOT NULL | 密码（加密） |
| email | String | NOT NULL, UNIQUE | 邮箱 |
| games | List&lt;Game&gt; | 1:N | 关联的游戏列表 |

**文件位置**: [User.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/User.java)

---

#### 2. Game
**表名**: `games`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 游戏ID |
| title | String | NOT NULL | 游戏标题 |
| currentState | String | NOT NULL | 当前状态 |
| createdAt | LocalDateTime | NOT NULL | 创建时间 |
| updatedAt | LocalDateTime | NOT NULL | 更新时间 |
| user | User | N:1, NOT NULL | 所属用户 |
| events | List&lt;GameEvent&gt; | 1:N | 游戏事件 |
| playerCharacters | List&lt;PlayerCharacter&gt; | 1:N | 玩家角色 |
| saveGames | List&lt;SaveGame&gt; | 1:N | 存档 |
| storyNodes | List&lt;StoryNode&gt; | 1:N | 故事节点 |
| combats | List&lt;Combat&gt; | 1:N | 战斗记录 |

**文件位置**: [Game.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/Game.java)

---

#### 3. PlayerCharacter
**表名**: `player_characters`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 角色ID |
| name | String | NOT NULL | 角色名称 |
| health | Integer | NOT NULL | 当前生命值 |
| maxHealth | Integer | NOT NULL | 最大生命值 |
| attack | Integer | NOT NULL | 攻击力 |
| defense | Integer | NOT NULL | 防御力 |
| morality | Integer | NOT NULL | 道德值 |
| knowledge | Integer | NOT NULL | 知识值 |
| skills | String | TEXT | 技能描述 |
| game | Game | N:1, NOT NULL | 所属游戏 |
| items | List&lt;Item&gt; | 1:N | 物品列表 |
| quests | List&lt;Quest&gt; | 1:N | 任务列表 |

**文件位置**: [PlayerCharacter.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/PlayerCharacter.java)

---

#### 4. StoryNode
**表名**: `story_nodes`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 节点ID |
| nodeType | String | NOT NULL | 节点类型 |
| content | String | TEXT, NOT NULL | 故事内容 |
| parentNode | StoryNode | N:1 | 父节点 |
| childNodes | List&lt;StoryNode&gt; | 1:N | 子节点 |
| game | Game | N:1, NOT NULL | 所属游戏 |

**文件位置**: [StoryNode.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/StoryNode.java)

---

#### 5. Combat
**表名**: `combats`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 战斗ID |
| status | CombatStatus | NOT NULL | 战斗状态 |
| currentTurn | Integer | NOT NULL | 当前回合 |
| combatLog | String | TEXT | 战斗日志 |
| enemyName | String | NOT NULL | 敌人名称 |
| enemyHealth | Integer | NOT NULL | 敌人当前生命值 |
| enemyMaxHealth | Integer | NOT NULL | 敌人最大生命值 |
| enemyAttack | Integer | NOT NULL | 敌人攻击力 |
| enemyDefense | Integer | NOT NULL | 敌人防御力 |
| game | Game | N:1, NOT NULL | 所属游戏 |

**文件位置**: [Combat.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/Combat.java)

---

#### 6. Quest
**表名**: `quests`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 任务ID |
| title | String | NOT NULL | 任务标题 |
| description | String | TEXT | 任务描述 |
| status | QuestStatus | NOT NULL | 任务状态 |
| progress | Integer | NOT NULL, DEFAULT 0 | 当前进度 |
| targetProgress | Integer | NOT NULL, DEFAULT 1 | 目标进度 |
| reward | String | TEXT | 奖励描述 |
| playerCharacter | PlayerCharacter | N:1, NOT NULL | 所属角色 |

**文件位置**: [Quest.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/Quest.java)

---

#### 7. Item
**表名**: `items`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 物品ID |
| name | String | NOT NULL | 物品名称 |
| description | String | TEXT | 物品描述 |
| type | String | NOT NULL | 物品类型 |
| attackBonus | Integer | - | 攻击加成 |
| defenseBonus | Integer | - | 防御加成 |
| healthBonus | Integer | - | 生命加成 |
| equipped | Boolean | NOT NULL, DEFAULT false | 是否装备 |
| playerCharacter | PlayerCharacter | N:1, NOT NULL | 所属角色 |

**文件位置**: [Item.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/Item.java)

---

#### 8. GameEvent
**表名**: `game_events`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 事件ID |
| eventType | String | NOT NULL | 事件类型 |
| description | String | NOT NULL, MAX 1000 | 事件描述 |
| timestamp | LocalDateTime | NOT NULL | 时间戳 |
| game | Game | N:1, NOT NULL | 所属游戏 |

**文件位置**: [GameEvent.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/GameEvent.java)

---

#### 9. SaveGame
**表名**: `save_games`

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | Long | PK, AI | 存档ID |
| slotNumber | Integer | NOT NULL | 存档槽位 |
| saveName | String | NOT NULL | 存档名称 |
| saveTime | LocalDateTime | NOT NULL | 存档时间 |
| gameState | String | TEXT, NOT NULL | 游戏状态 |
| game | Game | N:1, NOT NULL | 所属游戏 |

**文件位置**: [SaveGame.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/SaveGame.java)

---

### 枚举类型

#### CombatStatus
**文件位置**: [CombatStatus.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/CombatStatus.java)

| 值 | 说明 |
|----|------|
| PENDING | 待开始 |
| IN_PROGRESS | 进行中 |
| PLAYER_WON | 玩家胜利 |
| ENEMY_WON | 敌人胜利 |

---

#### QuestStatus
**文件位置**: [QuestStatus.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/model/QuestStatus.java)

| 值 | 说明 |
|----|------|
| PENDING | 待开始 |
| IN_PROGRESS | 进行中 |
| COMPLETED | 已完成 |
| FAILED | 已失败 |

---

## API 端点

### 基础信息
- **基础路径**: `/api`
- **端口**: `8080`
- **认证方式**: HTTP Basic
- **API 文档**: `/swagger-ui.html`
- **OpenAPI JSON**: `/v3/api-docs`

---

### 用户管理 (UserController)
**路径前缀**: `/users`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | / | 创建用户 |
| GET | /{id} | 获取用户详情 |
| GET | / | 获取所有用户 |
| PUT | /{id} | 更新用户 |
| DELETE | /{id} | 删除用户 |

**文件位置**: [UserController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/UserController.java)

---

### 游戏管理 (GameController)
**路径前缀**: `/games`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | / | 创建游戏 |
| GET | /{id} | 获取游戏详情 |
| GET | /user/{userId} | 获取用户的所有游戏 |
| PUT | /{id} | 更新游戏 |
| DELETE | /{id} | 删除游戏 |
| POST | /{id}/action | 处理玩家动作 |

**文件位置**: [GameController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/GameController.java)

---

### 故事管理 (StoryController)
**路径前缀**: `/stories`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | /initialize/{gameId} | 初始化游戏故事 |
| POST | /{gameId}/action | 处理玩家动作 |
| GET | /{gameId}/history | 获取故事历史 |
| GET | /{gameId}/actions | 获取可用动作选项 |

**文件位置**: [StoryController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/StoryController.java)

---

### 角色管理 (PlayerCharacterController)
**路径前缀**: `/characters`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | / | 创建角色 |
| GET | /{id} | 获取角色详情 |
| GET | /game/{gameId} | 获取游戏的所有角色 |
| PUT | /{id} | 更新角色 |
| DELETE | /{id} | 删除角色 |

**文件位置**: [PlayerCharacterController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/PlayerCharacterController.java)

---

### 战斗管理 (CombatController)
**路径前缀**: `/combats`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | / | 创建战斗 |
| GET | /{id} | 获取战斗详情 |
| GET | /game/{gameId} | 获取游戏的所有战斗 |
| PUT | /{id} | 更新战斗 |
| DELETE | /{id} | 删除战斗 |

**文件位置**: [CombatController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/CombatController.java)

---

### 任务管理 (QuestController)
**路径前缀**: `/quests`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | / | 创建任务 |
| GET | /{id} | 获取任务详情 |
| GET | /character/{characterId} | 获取角色的所有任务 |
| PUT | /{id} | 更新任务 |
| DELETE | /{id} | 删除任务 |

**文件位置**: [QuestController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/QuestController.java)

---

### 物品管理 (ItemController)
**路径前缀**: `/items`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | / | 创建物品 |
| GET | /{id} | 获取物品详情 |
| GET | /character/{characterId} | 获取角色的所有物品 |
| PUT | /{id} | 更新物品 |
| DELETE | /{id} | 删除物品 |

**文件位置**: [ItemController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/ItemController.java)

---

### 存档管理 (SaveGameController)
**路径前缀**: `/saves`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | / | 创建存档 |
| GET | /{id} | 获取存档详情 |
| GET | /game/{gameId} | 获取游戏的所有存档 |
| PUT | /{id} | 更新存档 |
| DELETE | /{id} | 删除存档 |

**文件位置**: [SaveGameController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/SaveGameController.java)

---

### 事件管理 (GameEventController)
**路径前缀**: `/events`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | / | 创建事件 |
| GET | /{id} | 获取事件详情 |
| GET | /game/{gameId} | 获取游戏的所有事件 |
| DELETE | /{id} | 删除事件 |

**文件位置**: [GameEventController.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/controller/GameEventController.java)

---

## 关键服务

### 1. DeepSeekClient - AI 客户端服务
核心服务，负责与 DeepSeek AI 模型进行交互。

**主要功能**:
- 生成冒险故事内容
- 生成角色对话
- 重试机制（最多 3 次，指数退避）
- 错误处理

**关键方法**:

| 方法 | 说明 |
|------|------|
| `generateStory(String prompt)` | 基于提示生成冒险故事 |
| `generateDialogue(String context, String character)` | 基于上下文生成角色对话 |
| `sendChatRequest(List&lt;DeepSeekMessage&gt;)` | 发送聊天请求到 DeepSeek API |

**文件位置**: [DeepSeekClient.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/DeepSeekClient.java)

---

### 2. GameService - 游戏服务
游戏管理的核心业务逻辑。

**接口方法**:

| 方法 | 说明 |
|------|------|
| `createGame(Game game)` | 创建新游戏 |
| `getGameById(Long id)` | 根据ID获取游戏 |
| `getGamesByUserId(Long userId)` | 获取用户的所有游戏 |
| `updateGame(Long id, Game game)` | 更新游戏信息 |
| `deleteGame(Long id)` | 删除游戏 |
| `processPlayerAction(Long gameId, String action)` | 处理玩家动作 |

**文件位置**: 
- 接口: [GameService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/GameService.java)
- 实现: [GameServiceImpl.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/GameServiceImpl.java)

---

### 3. StoryService - 故事服务
故事生成和管理的核心业务逻辑。

**接口方法**:

| 方法 | 说明 |
|------|------|
| `initializeGame(Long gameId)` | 初始化游戏故事 |
| `processPlayerAction(Long gameId, String playerAction)` | 处理玩家动作并推进故事 |
| `getStoryHistory(Long gameId)` | 获取故事历史记录 |
| `generateActionOptions(Long gameId)` | 生成可用动作选项 |
| `createStoryNode(Game, String, String, StoryNode)` | 创建故事节点 |

**文件位置**: 
- 接口: [StoryService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/StoryService.java)
- 实现: [StoryServiceImpl.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/StoryServiceImpl.java)

---

### 其他服务接口

| 服务 | 说明 | 文件位置 |
|------|------|----------|
| UserService | 用户管理 | [UserService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/UserService.java) |
| PlayerCharacterService | 角色管理 | [PlayerCharacterService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/PlayerCharacterService.java) |
| CombatService | 战斗管理 | [CombatService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/CombatService.java) |
| QuestService | 任务管理 | [QuestService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/QuestService.java) |
| ItemService | 物品管理 | [ItemService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/ItemService.java) |
| SaveGameService | 存档管理 | [SaveGameService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/SaveGameService.java) |
| GameEventService | 事件管理 | [GameEventService.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/service/GameEventService.java) |

---

## 配置说明

### 应用配置 (application.properties)
**文件位置**: [application.properties](file:///workspace/ai-adventure-api/src/main/resources/application.properties)

#### 服务器配置
```properties
server.port=8080
server.servlet.context-path=/api
```

#### 数据库配置
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aiadventure
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
```

#### JPA 配置
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

#### 安全配置
```properties
spring.security.user.name=admin
spring.security.user.password=admin123
```

#### OpenAPI 配置
```properties
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

#### DeepSeek API 配置
```properties
deepseek.api.key=your-deepseek-api-key-here
deepseek.api.base-url=https://api.deepseek.com
deepseek.api.model=deepseek-chat
deepseek.api.temperature=0.7
deepseek.api.max-tokens=2000
```

### 安全配置 (SecurityConfig)
**文件位置**: [SecurityConfig.java](file:///workspace/ai-adventure-api/src/main/java/com/example/aiadventure/config/SecurityConfig.java)

**主要配置**:
- CSRF 保护已禁用
- Swagger UI 和 API 文档端点允许公开访问
- 其他所有端点需要 HTTP Basic 认证
- 使用 BCryptPasswordEncoder 进行密码加密

---

## 运行方式

### 前置要求
1. Java 17 或更高版本
2. Maven 3.6+
3. PostgreSQL 数据库
4. DeepSeek API 密钥

### 数据库设置
1. 创建 PostgreSQL 数据库:
```sql
CREATE DATABASE aiadventure;
```

2. 更新 [application.properties](file:///workspace/ai-adventure-api/src/main/resources/application.properties) 中的数据库连接信息

### DeepSeek API 设置
1. 获取 DeepSeek API 密钥
2. 更新 [application.properties](file:///workspace/ai-adventure-api/src/main/resources/application.properties) 中的 `deepseek.api.key`

### 构建项目
```bash
cd ai-adventure-api
mvn clean install
```

### 运行应用
```bash
mvn spring-boot:run
```

或者运行打包后的 JAR 文件:
```bash
java -jar target/ai-adventure-api-0.0.1-SNAPSHOT.jar
```

### 访问应用
- **应用地址**: http://localhost:8080/api
- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **API 文档**: http://localhost:8080/api/v3/api-docs

### 认证信息
- 默认用户名: `admin`
- 默认密码: `admin123`

---

## 项目文件结构

```
/workspace/
├── ai-adventure-api/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/aiadventure/
│   │       │   ├── config/
│   │       │   │   └── SecurityConfig.java
│   │       │   ├── controller/
│   │       │   │   ├── CombatController.java
│   │       │   │   ├── GameController.java
│   │       │   │   ├── GameEventController.java
│   │       │   │   ├── ItemController.java
│   │       │   │   ├── PlayerCharacterController.java
│   │       │   │   ├── QuestController.java
│   │       │   │   ├── SaveGameController.java
│   │       │   │   ├── StoryController.java
│   │       │   │   └── UserController.java
│   │       │   ├── dto/
│   │       │   │   ├── DeepSeekChatRequest.java
│   │       │   │   ├── DeepSeekChatResponse.java
│   │       │   │   ├── DeepSeekChoice.java
│   │       │   │   ├── DeepSeekMessage.java
│   │       │   │   └── DeepSeekUsage.java
│   │       │   ├── model/
│   │       │   │   ├── Combat.java
│   │       │   │   ├── CombatStatus.java
│   │       │   │   ├── Game.java
│   │       │   │   ├── GameEvent.java
│   │       │   │   ├── Item.java
│   │       │   │   ├── PlayerCharacter.java
│   │       │   │   ├── Quest.java
│   │       │   │   ├── QuestStatus.java
│   │       │   │   ├── SaveGame.java
│   │       │   │   ├── StoryNode.java
│   │       │   │   └── User.java
│   │       │   ├── repository/
│   │       │   │   ├── CombatRepository.java
│   │       │   │   ├── GameEventRepository.java
│   │       │   │   ├── GameRepository.java
│   │       │   │   ├── ItemRepository.java
│   │       │   │   ├── PlayerCharacterRepository.java
│   │       │   │   ├── QuestRepository.java
│   │       │   │   ├── SaveGameRepository.java
│   │       │   │   ├── StoryNodeRepository.java
│   │       │   │   └── UserRepository.java
│   │       │   ├── service/
│   │       │   │   ├── CombatService.java
│   │       │   │   ├── CombatServiceImpl.java
│   │       │   │   ├── DeepSeekClient.java
│   │       │   │   ├── GameEventService.java
│   │       │   │   ├── GameEventServiceImpl.java
│   │       │   │   ├── GameService.java
│   │       │   │   ├── GameServiceImpl.java
│   │       │   │   ├── ItemService.java
│   │       │   │   ├── ItemServiceImpl.java
│   │       │   │   ├── PlayerCharacterService.java
│   │       │   │   ├── PlayerCharacterServiceImpl.java
│   │       │   │   ├── QuestService.java
│   │       │   │   ├── QuestServiceImpl.java
│   │       │   │   ├── SaveGameService.java
│   │       │   │   ├── SaveGameServiceImpl.java
│   │       │   │   ├── StoryService.java
│   │       │   │   ├── StoryServiceImpl.java
│   │       │   │   ├── UserService.java
│   │       │   │   └── UserServiceImpl.java
│   │       │   └── AiAdventureApiApplication.java
│   │       └── resources/
│   │           └── application.properties
│   └── pom.xml
├── .gitignore
├── LICENSE
├── README.md
├── merge-summary.md
└── CODE_WIKI.md (本文件)
```

---

## 总结

AI Adventure API 是一个功能完整的后端服务，为 AI 驱动的文本冒险游戏提供强大支持。项目采用现代化的 Spring Boot 架构，具有良好的分层设计和可扩展性。通过 DeepSeek AI 集成，实现了动态故事生成，为玩家提供独特的冒险体验。

如需进一步了解特定模块的实现细节，请参考相应的源代码文件。
