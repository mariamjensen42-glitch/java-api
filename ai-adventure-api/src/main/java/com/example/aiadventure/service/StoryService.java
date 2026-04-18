package com.example.aiadventure.service;

import com.example.aiadventure.model.Game;
import com.example.aiadventure.model.StoryNode;
import java.util.List;

public interface StoryService {

    void initializeGame(Long gameId);

    StoryNode processPlayerAction(Long gameId, String playerAction);

    List<StoryNode> getStoryHistory(Long gameId);

    List<String> generateActionOptions(Long gameId);

    StoryNode createStoryNode(Game game, String nodeType, String content, StoryNode parentNode);

}
