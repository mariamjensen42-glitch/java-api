package com.example.aiadventure.service;

import com.example.aiadventure.model.PlayerCharacter;
import com.example.aiadventure.repository.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerCharacterServiceImpl implements PlayerCharacterService {

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Override
    public PlayerCharacter createPlayerCharacter(PlayerCharacter character) {
        return playerCharacterRepository.save(character);
    }

    @Override
    public PlayerCharacter getPlayerCharacter(Long id) {
        return playerCharacterRepository.findById(id).orElse(null);
    }

    @Override
    public List<PlayerCharacter> getPlayerCharacterByGameId(Long gameId) {
        return playerCharacterRepository.findByGameId(gameId);
    }

    @Override
    public PlayerCharacter updatePlayerCharacter(Long id, PlayerCharacter character) {
        PlayerCharacter existingCharacter = playerCharacterRepository.findById(id).orElse(null);
        if (existingCharacter != null) {
            existingCharacter.setName(character.getName());
            existingCharacter.setHealth(character.getHealth());
            existingCharacter.setMaxHealth(character.getMaxHealth());
            existingCharacter.setAttack(character.getAttack());
            existingCharacter.setDefense(character.getDefense());
            existingCharacter.setMorality(character.getMorality());
            existingCharacter.setKnowledge(character.getKnowledge());
            existingCharacter.setSkills(character.getSkills());
            return playerCharacterRepository.save(existingCharacter);
        }
        return null;
    }

    @Override
    public PlayerCharacter updateHealth(Long id, Integer amount) {
        PlayerCharacter character = playerCharacterRepository.findById(id).orElse(null);
        if (character != null) {
            int newHealth = character.getHealth() + amount;
            if (newHealth < 0) {
                newHealth = 0;
            } else if (newHealth > character.getMaxHealth()) {
                newHealth = character.getMaxHealth();
            }
            character.setHealth(newHealth);
            return playerCharacterRepository.save(character);
        }
        return null;
    }

    @Override
    public PlayerCharacter updateMorality(Long id, Integer amount) {
        PlayerCharacter character = playerCharacterRepository.findById(id).orElse(null);
        if (character != null) {
            character.setMorality(character.getMorality() + amount);
            return playerCharacterRepository.save(character);
        }
        return null;
    }

    @Override
    public PlayerCharacter updateKnowledge(Long id, Integer amount) {
        PlayerCharacter character = playerCharacterRepository.findById(id).orElse(null);
        if (character != null) {
            character.setKnowledge(character.getKnowledge() + amount);
            return playerCharacterRepository.save(character);
        }
        return null;
    }
}
