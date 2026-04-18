package com.example.aiadventure.service;

import com.example.aiadventure.dto.DeepSeekMessage;
import com.example.aiadventure.model.Game;
import com.example.aiadventure.model.PlayerCharacter;
import com.example.aiadventure.model.StoryNode;
import com.example.aiadventure.repository.GameRepository;
import com.example.aiadventure.repository.PlayerCharacterRepository;
import com.example.aiadventure.repository.StoryNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryNodeRepository storyNodeRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    private DeepSeekClient deepSeekClient;

    @Override
    public void initializeGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> 
            new RuntimeException("Game not found with id: " + gameId)
        );

        PlayerCharacter playerCharacter = new PlayerCharacter();
        playerCharacter.setName("冒险英雄");
        playerCharacter.setHealth(100);
        playerCharacter.setMaxHealth(100);
        playerCharacter.setAttack(10);
        playerCharacter.setDefense(5);
        playerCharacter.setMorality(50);
        playerCharacter.setKnowledge(50);
        playerCharacter.setSkills("剑术, 魔法基础, 生存技能");
        playerCharacter.setGame(game);
        playerCharacterRepository.save(playerCharacter);

        String initialPrompt = "你是一个冒险游戏的叙述者。请创建一个引人入胜的奇幻冒险故事的开场。" +
                "故事应该包含一个神秘的场景、一些需要探索的元素，并让玩家感到好奇和兴奋。" +
                "请用中文写大约150-200字。";

        String initialStory = deepSeekClient.generateStory(initialPrompt);
        createStoryNode(game, "OPENING", initialStory, null);
    }

    @Override
    public StoryNode processPlayerAction(Long gameId, String playerAction) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> 
            new RuntimeException("Game not found with id: " + gameId)
        );

        List<StoryNode> storyHistory = getStoryHistory(gameId);
        StoryNode currentNode = storyHistory.isEmpty() ? null : storyHistory.get(storyHistory.size() - 1);

        StringBuilder context = new StringBuilder();
        for (StoryNode node : storyHistory) {
            context.append(node.getContent()).append("\n\n");
        }

        String prompt = String.format(
                "作为冒险游戏的叙述者，请基于以下故事上下文和玩家的动作继续生成故事。\n\n" +
                "故事上下文：\n%s\n" +
                "玩家动作：%s\n\n" +
                "请继续故事，用中文写大约100-150字。确保故事连贯且有趣。",
                context.toString(),
                playerAction
        );

        String storyContent = deepSeekClient.generateStory(prompt);
        return createStoryNode(game, "CONTINUATION", storyContent, currentNode);
    }

    @Override
    public List<StoryNode> getStoryHistory(Long gameId) {
        List<StoryNode> allNodes = storyNodeRepository.findByGameId(gameId);
        List<StoryNode> orderedHistory = new ArrayList<>();

        StoryNode currentNode = allNodes.stream()
                .filter(node -> node.getParentNode() == null)
                .findFirst()
                .orElse(null);

        while (currentNode != null) {
            orderedHistory.add(currentNode);
            final StoryNode finalCurrentNode = currentNode;
            currentNode = allNodes.stream()
                    .filter(node -> node.getParentNode() != null && node.getParentNode().getId().equals(finalCurrentNode.getId()))
                    .findFirst()
                    .orElse(null);
        }

        return orderedHistory;
    }

    @Override
    public List<String> generateActionOptions(Long gameId) {
        List<StoryNode> storyHistory = getStoryHistory(gameId);
        if (storyHistory.isEmpty()) {
            return Collections.emptyList();
        }

        StoryNode currentNode = storyHistory.get(storyHistory.size() - 1);
        String prompt = String.format(
                "基于以下冒险故事的当前场景，请生成3-5个合理的玩家行动选项。\n\n" +
                "当前场景：\n%s\n\n" +
                "请只返回行动选项，每个选项一行，不要有编号或其他格式。用中文。",
                currentNode.getContent()
        );

        List<DeepSeekMessage> messages = Collections.singletonList(
                new DeepSeekMessage("user", prompt)
        );

        String response = deepSeekClient.generateStory(prompt);
        String[] options = response.split("\n");
        List<String> cleanOptions = new ArrayList<>();
        for (String option : options) {
            String trimmed = option.trim();
            if (!trimmed.isEmpty()) {
                cleanOptions.add(trimmed);
            }
        }

        return cleanOptions.subList(0, Math.min(5, cleanOptions.size()));
    }

    @Override
    public StoryNode createStoryNode(Game game, String nodeType, String content, StoryNode parentNode) {
        StoryNode storyNode = new StoryNode();
        storyNode.setGame(game);
        storyNode.setNodeType(nodeType);
        storyNode.setContent(content);
        storyNode.setParentNode(parentNode);
        return storyNodeRepository.save(storyNode);
    }
}
