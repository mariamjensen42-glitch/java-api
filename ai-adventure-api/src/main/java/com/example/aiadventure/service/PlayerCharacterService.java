package com.example.aiadventure.service;

import com.example.aiadventure.model.PlayerCharacter;
import java.util.List;

public interface PlayerCharacterService {

    PlayerCharacter createPlayerCharacter(PlayerCharacter character);

    PlayerCharacter getPlayerCharacter(Long id);

    List<PlayerCharacter> getPlayerCharacterByGameId(Long gameId);

    PlayerCharacter updatePlayerCharacter(Long id, PlayerCharacter character);

    PlayerCharacter updateHealth(Long id, Integer amount);

    PlayerCharacter updateMorality(Long id, Integer amount);

    PlayerCharacter updateKnowledge(Long id, Integer amount);
}
