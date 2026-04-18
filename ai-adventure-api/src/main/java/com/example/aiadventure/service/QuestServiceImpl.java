package com.example.aiadventure.service;

import com.example.aiadventure.model.PlayerCharacter;
import com.example.aiadventure.model.Quest;
import com.example.aiadventure.model.QuestStatus;
import com.example.aiadventure.repository.PlayerCharacterRepository;
import com.example.aiadventure.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Override
    public Quest acceptQuest(Long playerCharacterId, Quest quest) {
        PlayerCharacter playerCharacter = playerCharacterRepository.findById(playerCharacterId)
                .orElseThrow(() -> new IllegalArgumentException("Player character not found"));
        
        quest.setPlayerCharacter(playerCharacter);
        quest.setStatus(QuestStatus.IN_PROGRESS);
        quest.setProgress(0);
        
        return questRepository.save(quest);
    }

    @Override
    public Quest getQuest(Long id) {
        return questRepository.findById(id).orElse(null);
    }

    @Override
    public List<Quest> getQuestsByPlayerCharacterId(Long characterId) {
        return questRepository.findByPlayerCharacterId(characterId);
    }

    @Override
    public Quest updateQuestProgress(Long id, Integer progress) {
        Quest quest = questRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quest not found"));
        
        quest.setProgress(progress);
        return questRepository.save(quest);
    }

    @Override
    public Quest completeQuest(Long id) {
        Quest quest = questRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quest not found"));
        
        quest.setStatus(QuestStatus.COMPLETED);
        quest.setProgress(quest.getTargetProgress());
        return questRepository.save(quest);
    }

    @Override
    public boolean checkQuestCompletion(Long id) {
        Quest quest = questRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quest not found"));
        
        return quest.getProgress() >= quest.getTargetProgress();
    }

}
