package com.example.aiadventure.repository;

import com.example.aiadventure.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByPlayerCharacterId(Long playerCharacterId);

    List<Item> findByType(String type);

}
