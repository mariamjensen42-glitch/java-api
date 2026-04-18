package com.example.aiadventure.controller;

import com.example.aiadventure.model.SaveGame;
import com.example.aiadventure.service.SaveGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/save-games")
public class SaveGameController {

    @Autowired
    private SaveGameService saveGameService;

    @PostMapping("/{gameId}/slots/{slotNumber}")
    public ResponseEntity<SaveGame> createSave(@PathVariable Long gameId, @PathVariable Integer slotNumber, @RequestBody Map<String, String> saveData) {
        String saveName = saveData.get("saveName");
        SaveGame saveGame = saveGameService.createSave(gameId, slotNumber, saveName);
        return new ResponseEntity<>(saveGame, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaveGame> getSaveGame(@PathVariable Long id) {
        SaveGame saveGame = saveGameService.getSaveGame(id);
        if (saveGame != null) {
            return new ResponseEntity<>(saveGame, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<SaveGame>> getSaveGamesByGameId(@PathVariable Long gameId) {
        List<SaveGame> saveGames = saveGameService.getSaveGamesByGameId(gameId);
        return new ResponseEntity<>(saveGames, HttpStatus.OK);
    }

    @PostMapping("/{id}/load")
    public ResponseEntity<SaveGame> loadSaveGame(@PathVariable Long id) {
        SaveGame saveGame = saveGameService.loadSaveGame(id);
        if (saveGame != null) {
            return new ResponseEntity<>(saveGame, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaveGame(@PathVariable Long id) {
        saveGameService.deleteSaveGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{gameId}/auto-save")
    public ResponseEntity<SaveGame> autoSave(@PathVariable Long gameId) {
        SaveGame saveGame = saveGameService.autoSave(gameId);
        return new ResponseEntity<>(saveGame, HttpStatus.CREATED);
    }
}
