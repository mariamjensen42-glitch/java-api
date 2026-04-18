package com.example.aiadventure.controller;

import com.example.aiadventure.model.StoryNode;
import com.example.aiadventure.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @PostMapping("/initialize/{gameId}")
    public ResponseEntity<Void> initializeGame(@PathVariable Long gameId) {
        storyService.initializeGame(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{gameId}/action")
    public ResponseEntity<StoryNode> processPlayerAction(@PathVariable Long gameId, @RequestBody Map<String, String> actionData) {
        String playerAction = actionData.get("action");
        StoryNode storyNode = storyService.processPlayerAction(gameId, playerAction);
        if (storyNode != null) {
            return new ResponseEntity<>(storyNode, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{gameId}/history")
    public ResponseEntity<List<StoryNode>> getStoryHistory(@PathVariable Long gameId) {
        List<StoryNode> history = storyService.getStoryHistory(gameId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/{gameId}/actions")
    public ResponseEntity<List<String>> getActionOptions(@PathVariable Long gameId) {
        List<String> actions = storyService.generateActionOptions(gameId);
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }
}
