package com.example.aiadventure.service;

import com.example.aiadventure.model.Item;
import java.util.List;
import java.util.Map;

public interface ItemService {

    Item createItem(Item item);

    Item getItem(Long id);

    List<Item> getItemsByPlayerCharacterId(Long characterId);

    Item equipItem(Long characterId, Long itemId);

    Item unequipItem(Long characterId, Long itemId);

    Map<String, Integer> calculateTotalStats(Long characterId);
}
