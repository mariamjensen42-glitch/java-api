package com.example.aiadventure.controller;

import com.example.aiadventure.model.Item;
import com.example.aiadventure.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item created = itemService.createItem(item);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Item item = itemService.getItem(id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/player/{playerCharacterId}")
    public ResponseEntity<List<Item>> getItemsByPlayerCharacterId(@PathVariable Long playerCharacterId) {
        List<Item> items = itemService.getItemsByPlayerCharacterId(playerCharacterId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/{id}/equip/{playerCharacterId}")
    public ResponseEntity<Item> equipItem(@PathVariable Long id, @PathVariable Long playerCharacterId) {
        Item item = itemService.equipItem(playerCharacterId, id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/unequip/{playerCharacterId}")
    public ResponseEntity<Item> unequipItem(@PathVariable Long id, @PathVariable Long playerCharacterId) {
        Item item = itemService.unequipItem(playerCharacterId, id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
