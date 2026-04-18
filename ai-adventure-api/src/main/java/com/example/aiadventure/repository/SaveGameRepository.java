package com.example.aiadventure.repository;

import com.example.aiadventure.model.SaveGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaveGameRepository extends JpaRepository<SaveGame, Long> {

    List<SaveGame> findByGameId(Long gameId);

    Optional<SaveGame> findByGameIdAndSlotNumber(Long gameId, Integer slotNumber);

}
