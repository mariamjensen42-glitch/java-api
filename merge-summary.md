此次合并引入了完整的AI冒险游戏API系统，包含用户管理、游戏管理、战斗系统、任务系统、物品系统和故事生成等核心功能，并集成了DeepSeek API用于AI故事生成。系统采用Spring Boot架构，包含完整的控制器、服务、仓库和模型层。
| 文件 | 变更 |
|------|---------|
| .trae/specs/story-management/checklist.md | 新增故事管理功能的检查清单，包含任务完成标准和验证步骤 |
| .trae/specs/story-management/spec.md | 新增故事管理功能的详细规格说明，定义功能需求和技术实现方案 |
| .trae/specs/story-management/tasks.md | 新增故事管理功能的任务分解，包含具体实现步骤和里程碑 |
| ai-adventure-api/pom.xml | 新增Maven项目配置文件，定义项目依赖和构建配置 |
| ai-adventure-api/src/main/java/com/example/aiadventure/AiAdventureApiApplication.java | 新增Spring Boot主应用类，启动整个API系统 |
| ai-adventure-api/src/main/java/com/example/aiadventure/config/SecurityConfig.java | 新增安全配置类，设置API访问权限和认证机制 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/CombatController.java | 新增战斗控制器，提供战斗相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/GameController.java | 新增游戏控制器，提供游戏管理相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/GameEventController.java | 新增游戏事件控制器，提供游戏事件相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/ItemController.java | 新增物品控制器，提供物品管理相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/PlayerCharacterController.java | 新增玩家角色控制器，提供角色管理相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/QuestController.java | 新增任务控制器，提供任务管理相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/SaveGameController.java | 新增存档控制器，提供游戏存档相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/StoryController.java | 新增故事控制器，提供故事节点相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/controller/UserController.java | 新增用户控制器，提供用户管理相关的API接口 |
| ai-adventure-api/src/main/java/com/example/aiadventure/dto/DeepSeekChatRequest.java | 新增DeepSeek API请求DTO，用于构建聊天请求 |
| ai-adventure-api/src/main/java/com/example/aiadventure/dto/DeepSeekChatResponse.java | 新增DeepSeek API响应DTO，用于解析聊天响应 |
| ai-adventure-api/src/main/java/com/example/aiadventure/dto/DeepSeekChoice.java | 新增DeepSeek API选择DTO，用于处理响应选项 |
| ai-adventure-api/src/main/java/com/example/aiadventure/dto/DeepSeekMessage.java | 新增DeepSeek API消息DTO，用于构建和处理消息 |
| ai-adventure-api/src/main/java/com/example/aiadventure/dto/DeepSeekUsage.java | 新增DeepSeek API使用统计DTO，用于跟踪API使用情况 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/Combat.java | 新增战斗模型类，定义战斗相关属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/CombatStatus.java | 新增战斗状态枚举类，定义战斗的不同状态 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/Game.java | 新增游戏模型类，定义游戏的核心属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/GameEvent.java | 新增游戏事件模型类，定义游戏事件的属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/Item.java | 新增物品模型类，定义物品的属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/PlayerCharacter.java | 新增玩家角色模型类，定义角色的属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/Quest.java | 新增任务模型类，定义任务的属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/QuestStatus.java | 新增任务状态枚举类，定义任务的不同状态 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/SaveGame.java | 新增存档模型类，定义存档的属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/StoryNode.java | 新增故事节点模型类，定义故事节点的属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/model/User.java | 新增用户模型类，定义用户的属性和关系 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/CombatRepository.java | 新增战斗仓库接口，提供战斗数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/GameEventRepository.java | 新增游戏事件仓库接口，提供游戏事件数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/GameRepository.java | 新增游戏仓库接口，提供游戏数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/ItemRepository.java | 新增物品仓库接口，提供物品数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/PlayerCharacterRepository.java | 新增玩家角色仓库接口，提供角色数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/QuestRepository.java | 新增任务仓库接口，提供任务数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/SaveGameRepository.java | 新增存档仓库接口，提供存档数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/StoryNodeRepository.java | 新增故事节点仓库接口，提供故事节点数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/repository/UserRepository.java | 新增用户仓库接口，提供用户数据的CRUD操作 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/CombatService.java | 新增战斗服务接口，定义战斗相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/CombatServiceImpl.java | 新增战斗服务实现类，实现战斗相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/DeepSeekClient.java | 新增DeepSeek API客户端，用于调用AI模型生成故事内容 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/GameEventService.java | 新增游戏事件服务接口，定义游戏事件相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/GameEventServiceImpl.java | 新增游戏事件服务实现类，实现游戏事件相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/GameService.java | 新增游戏服务接口，定义游戏相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/GameServiceImpl.java | 新增游戏服务实现类，实现游戏相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/ItemService.java | 新增物品服务接口，定义物品相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/ItemServiceImpl.java | 新增物品服务实现类，实现物品相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/PlayerCharacterService.java | 新增玩家角色服务接口，定义角色相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/PlayerCharacterServiceImpl.java | 新增玩家角色服务实现类，实现角色相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/QuestService.java | 新增任务服务接口，定义任务相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/QuestServiceImpl.java | 新增任务服务实现类，实现任务相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/SaveGameService.java | 新增存档服务接口，定义存档相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/SaveGameServiceImpl.java | 新增存档服务实现类，实现存档相关的业务逻辑，包括游戏状态序列化和反序列化 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/StoryService.java | 新增故事服务接口，定义故事相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/StoryServiceImpl.java | 新增故事服务实现类，实现故事相关的业务逻辑，包括AI故事生成和行动选项推荐 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/UserService.java | 新增用户服务接口，定义用户相关的业务逻辑 |
| ai-adventure-api/src/main/java/com/example/aiadventure/service/UserServiceImpl.java | 新增用户服务实现类，实现用户相关的业务逻辑，包括密码加密 |
| ai-adventure-api/src/main/resources/application.properties | 新增应用配置文件，定义服务器、数据库、安全和DeepSeek API配置 |