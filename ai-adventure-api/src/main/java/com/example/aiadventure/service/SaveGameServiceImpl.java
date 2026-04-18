package com.example.aiadventure.service;

import com.example.aiadventure.model.Game;
import com.example.aiadventure.model.SaveGame;
import com.example.aiadventure.repository.GameRepository;
import com.example.aiadventure.repository.SaveGameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SaveGameServiceImpl implements SaveGameService {

    @Autowired
    private SaveGameRepository saveGameRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public SaveGame createSave(Long gameId, Integer slotNumber, String saveName) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null) {
            return null;
        }

        Optional<SaveGame> existingSave = saveGameRepository.findByGameIdAndSlotNumber(gameId, slotNumber);
        SaveGame saveGame;
        if (existingSave.isPresent()) {
            saveGame = existingSave.get();
        } else {
            saveGame = new SaveGame();
        }

        saveGame.setGame(game);
        saveGame.setSlotNumber(slotNumber);
        saveGame.setSaveName(saveName);
        saveGame.setSaveTime(LocalDateTime.now());
        
        String gameStateJson = serializeGameState(game);
        saveGame.setGameState(gameStateJson);

        return saveGameRepository.save(saveGame);
    }

    @Override
    public SaveGame getSaveGame(Long id) {
        return saveGameRepository.findById(id).orElse(null);
    }

    @Override
    public List<SaveGame> getSaveGamesByGameId(Long gameId) {
        return saveGameRepository.findByGameId(gameId);
    }

    @Override
    public SaveGame loadSaveGame(Long saveId) {
        SaveGame saveGame = saveGameRepository.findById(saveId).orElse(null);
        if (saveGame == null) {
            return null;
        }

        Game game = saveGame.getGame();
        if (game == null) {
            return null;
        }

        deserializeGameState(game, saveGame.getGameState());
        gameRepository.save(game);

        return saveGame;
    }

    @Override
    public void deleteSaveGame(Long id) {
        saveGameRepository.deleteById(id);
    }

    @Override
    public SaveGame autoSave(Long gameId) {
        String autoSaveName = "Auto Save - " + LocalDateTime.now().toString();
        return createSave(gameId, 0, autoSaveName);
    }

    private String serializeGameState(Game game) {
        try {
            Map<String, Object> gameState = new HashMap<>();
            gameState.put("currentState", game.getCurrentState());
            gameState.put("title", game.getTitle());
            gameState.put("events", game.getEvents());
            gameState.put("playerCharacters", game.getPlayerCharacters());
            gameState.put("storyNodes", game.getStoryNodes());
            gameState.put("combats", game.getCombats());
            
            return objectMapper.writeValueAsString(gameState);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize game state", e);
        }
    }

    private void deserializeGameState(Game game, String gameStateJson) {
        try {
            Map<String, Object> gameState = objectMapper.readValue(gameStateJson, Map.class);
            
            if (gameState.containsKey("currentState")) {
                game.setCurrentState((String) gameState.get("currentState"));
            }
            if (gameState.containsKey("title")) {
                game.setTitle((String) gameState.get("title"));
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize game state", e);
        }
    }
}
