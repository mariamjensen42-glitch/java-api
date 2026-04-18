package com.example.aiadventure.repository;

import com.example.aiadventure.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

    List<Quest> findByPlayerCharacterId(Long playerCharacterId);

    List<Quest> findByStatus(String status);

}
