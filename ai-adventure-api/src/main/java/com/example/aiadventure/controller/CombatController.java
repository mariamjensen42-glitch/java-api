package com.example.aiadventure.controller;

import com.example.aiadventure.model.Combat;
import com.example.aiadventure.service.CombatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/combats")
public class CombatController {

    @Autowired
    private CombatService combatService;

    @PostMapping("/start/{gameId}")
    public ResponseEntity<Combat> startCombat(@PathVariable Long gameId, @RequestBody Map<String, Object> enemyData) {
        String enemyName = (String) enemyData.get("enemyName");
        Integer enemyHealth = (Integer) enemyData.get("enemyHealth");
        Integer enemyAttack = (Integer) enemyData.get("enemyAttack");
        Integer enemyDefense = (Integer) enemyData.get("enemyDefense");
        Combat combat = combatService.startCombat(gameId, enemyName, enemyHealth, enemyAttack, enemyDefense);
        return new ResponseEntity<>(combat, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Combat> getCombat(@PathVariable Long id) {
        Combat combat = combatService.getCombat(id);
        if (combat != null) {
            return new ResponseEntity<>(combat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Combat> getCombatByGameId(@PathVariable Long gameId) {
        Combat combat = combatService.getCombatByGameId(gameId);
        if (combat != null) {
            return new ResponseEntity<>(combat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/player-attack")
    public ResponseEntity<Combat> playerAttack(@PathVariable Long id) {
        Combat combat = combatService.playerAttack(id);
        if (combat != null) {
            return new ResponseEntity<>(combat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/enemy-turn")
    public ResponseEntity<Combat> enemyTurn(@PathVariable Long id) {
        Combat combat = combatService.enemyTurn(id);
        if (combat != null) {
            return new ResponseEntity<>(combat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/log")
    public ResponseEntity<String> getCombatLog(@PathVariable Long id) {
        String log = combatService.getCombatLog(id);
        if (log != null) {
            return new ResponseEntity<>(log, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
