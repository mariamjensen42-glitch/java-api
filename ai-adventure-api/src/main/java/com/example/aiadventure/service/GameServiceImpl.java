package com.example.aiadventure.service;

import com.example.aiadventure.model.Game;
import com.example.aiadventure.model.GameEvent;
import com.example.aiadventure.repository.GameRepository;
import com.example.aiadventure.repository.GameEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameEventRepository gameEventRepository;

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public List<Game> getGamesByUserId(Long userId) {
        return gameRepository.findByUserId(userId);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        Game existingGame = gameRepository.findById(id).orElse(null);
        if (existingGame != null) {
            existingGame.setTitle(game.getTitle());
            existingGame.setCurrentState(game.getCurrentState());
            return gameRepository.save(existingGame);
        }
        return null;
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game processPlayerAction(Long gameId, String action) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game != null) {
            // 模拟AI处理玩家动作的逻辑
            String newState = generateNewState(game.getCurrentState(), action);
            game.setCurrentState(newState);
            
            // 创建游戏事件
            GameEvent event = new GameEvent();
            event.setEventType("PLAYER_ACTION");
            event.setDescription("Player action: " + action);
            event.setGame(game);
            gameEventRepository.save(event);
            
            return gameRepository.save(game);
        }
        return null;
    }

    private String generateNewState(String currentState, String action) {
        // 这里是模拟的AI逻辑，实际项目中可以集成真实的AI模型
        return "You performed action: " + action + "\n" + 
               "The game world responds: This is a simulated response based on your action.";
    }

}