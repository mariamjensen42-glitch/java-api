package com.example.aiadventure.controller;

import com.example.aiadventure.model.GameEvent;
import com.example.aiadventure.service.GameEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-events")
public class GameEventController {

    @Autowired
    private GameEventService gameEventService;

    @PostMapping
    public ResponseEntity<GameEvent> createGameEvent(@RequestBody GameEvent gameEvent) {
        GameEvent createdGameEvent = gameEventService.createGameEvent(gameEvent);
        return new ResponseEntity<>(createdGameEvent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameEvent> getGameEventById(@PathVariable Long id) {
        GameEvent gameEvent = gameEventService.getGameEventById(id);
        if (gameEvent != null) {
            return new ResponseEntity<>(gameEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<GameEvent>> getGameEventsByGameId(@PathVariable Long gameId) {
        List<GameEvent> gameEvents = gameEventService.getGameEventsByGameId(gameId);
        return new ResponseEntity<>(gameEvents, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameEvent(@PathVariable Long id) {
        gameEventService.deleteGameEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}