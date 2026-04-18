package com.example.aiadventure.service;

import com.example.aiadventure.model.SaveGame;
import java.util.List;

public interface SaveGameService {

    SaveGame createSave(Long gameId, Integer slotNumber, String saveName);

    SaveGame getSaveGame(Long id);

    List<SaveGame> getSaveGamesByGameId(Long gameId);

    SaveGame loadSaveGame(Long saveId);

    void deleteSaveGame(Long id);

    SaveGame autoSave(Long gameId);
}
