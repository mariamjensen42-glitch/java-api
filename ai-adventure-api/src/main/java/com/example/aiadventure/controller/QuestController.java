package com.example.aiadventure.controller;

import com.example.aiadventure.model.Quest;
import com.example.aiadventure.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quests")
public class QuestController {

    @Autowired
    private QuestService questService;

    @PostMapping("/accept/{playerCharacterId}")
    public ResponseEntity<Quest> acceptQuest(@PathVariable Long playerCharacterId, @RequestBody Quest quest) {
        Quest accepted = questService.acceptQuest(playerCharacterId, quest);
        return new ResponseEntity<>(accepted, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quest> getQuest(@PathVariable Long id) {
        Quest quest = questService.getQuest(id);
        if (quest != null) {
            return new ResponseEntity<>(quest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/player/{playerCharacterId}")
    public ResponseEntity<List<Quest>> getQuestsByPlayerCharacterId(@PathVariable Long playerCharacterId) {
        List<Quest> quests = questService.getQuestsByPlayerCharacterId(playerCharacterId);
        return new ResponseEntity<>(quests, HttpStatus.OK);
    }

    @PatchMapping("/{id}/progress")
    public ResponseEntity<Quest> updateQuestProgress(@PathVariable Long id, @RequestBody Map<String, Integer> progress) {
        Quest updated = questService.updateQuestProgress(id, progress.get("progress"));
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Quest> completeQuest(@PathVariable Long id) {
        Quest completed = questService.completeQuest(id);
        if (completed != null) {
            return new ResponseEntity<>(completed, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
