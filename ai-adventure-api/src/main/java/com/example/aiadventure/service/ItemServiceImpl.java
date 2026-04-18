package com.example.aiadventure.service;

import com.example.aiadventure.model.Item;
import com.example.aiadventure.model.PlayerCharacter;
import com.example.aiadventure.repository.ItemRepository;
import com.example.aiadventure.repository.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public List<Item> getItemsByPlayerCharacterId(Long characterId) {
        return itemRepository.findByPlayerCharacterId(characterId);
    }

    @Override
    public Item equipItem(Long characterId, Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item != null && item.getPlayerCharacter().getId().equals(characterId)) {
            item.setEquipped(true);
            return itemRepository.save(item);
        }
        return null;
    }

    @Override
    public Item unequipItem(Long characterId, Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item != null && item.getPlayerCharacter().getId().equals(characterId)) {
            item.setEquipped(false);
            return itemRepository.save(item);
        }
        return null;
    }

    @Override
    public Map<String, Integer> calculateTotalStats(Long characterId) {
        PlayerCharacter character = playerCharacterRepository.findById(characterId).orElse(null);
        if (character == null) {
            return null;
        }

        List<Item> items = itemRepository.findByPlayerCharacterId(characterId);

        int totalAttack = character.getAttack();
        int totalDefense = character.getDefense();
        int totalHealth = character.getMaxHealth();

        for (Item item : items) {
            if (item.getEquipped()) {
                if (item.getAttackBonus() != null) {
                    totalAttack += item.getAttackBonus();
                }
                if (item.getDefenseBonus() != null) {
                    totalDefense += item.getDefenseBonus();
                }
                if (item.getHealthBonus() != null) {
                    totalHealth += item.getHealthBonus();
                }
            }
        }

        Map<String, Integer> stats = new HashMap<>();
        stats.put("attack", totalAttack);
        stats.put("defense", totalDefense);
        stats.put("health", totalHealth);
        stats.put("morality", character.getMorality());
        stats.put("knowledge", character.getKnowledge());

        return stats;
    }
}
