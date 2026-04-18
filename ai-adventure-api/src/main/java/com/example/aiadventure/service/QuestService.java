package com.example.aiadventure.service;

import com.example.aiadventure.model.Quest;

import java.util.List;

public interface QuestService {

    Quest acceptQuest(Long playerCharacterId, Quest quest);

    Quest getQuest(Long id);

    List<Quest> getQuestsByPlayerCharacterId(Long characterId);

    Quest updateQuestProgress(Long id, Integer progress);

    Quest completeQuest(Long id);

    boolean checkQuestCompletion(Long id);

}
