package com.example.aiadventure.controller;

import com.example.aiadventure.model.PlayerCharacter;
import com.example.aiadventure.service.ItemService;
import com.example.aiadventure.service.PlayerCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/player-characters")
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterService playerCharacterService;

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<PlayerCharacter> createPlayerCharacter(@RequestBody PlayerCharacter character) {
        PlayerCharacter created = playerCharacterService.createPlayerCharacter(character);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerCharacter> getPlayerCharacter(@PathVariable Long id) {
        PlayerCharacter character = playerCharacterService.getPlayerCharacter(id);
        if (character != null) {
            return new ResponseEntity<>(character, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<PlayerCharacter>> getPlayerCharactersByGameId(@PathVariable Long gameId) {
        List<PlayerCharacter> characters = playerCharacterService.getPlayerCharacterByGameId(gameId);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerCharacter> updatePlayerCharacter(@PathVariable Long id, @RequestBody PlayerCharacter character) {
        PlayerCharacter updated = playerCharacterService.updatePlayerCharacter(id, character);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/health")
    public ResponseEntity<PlayerCharacter> updateHealth(@PathVariable Long id, @RequestBody Map<String, Integer> amount) {
        PlayerCharacter updated = playerCharacterService.updateHealth(id, amount.get("amount"));
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/morality")
    public ResponseEntity<PlayerCharacter> updateMorality(@PathVariable Long id, @RequestBody Map<String, Integer> amount) {
        PlayerCharacter updated = playerCharacterService.updateMorality(id, amount.get("amount"));
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/knowledge")
    public ResponseEntity<PlayerCharacter> updateKnowledge(@PathVariable Long id, @RequestBody Map<String, Integer> amount) {
        PlayerCharacter updated = playerCharacterService.updateKnowledge(id, amount.get("amount"));
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/total-stats")
    public ResponseEntity<Map<String, Integer>> getTotalStats(@PathVariable Long id) {
        Map<String, Integer> stats = itemService.calculateTotalStats(id);
        if (stats != null) {
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
