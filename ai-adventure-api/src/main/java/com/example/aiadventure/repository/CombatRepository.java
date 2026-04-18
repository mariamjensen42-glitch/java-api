package com.example.aiadventure.repository;

import com.example.aiadventure.model.Combat;
import com.example.aiadventure.model.CombatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CombatRepository extends JpaRepository<Combat, Long> {

    List<Combat> findByGameId(Long gameId);

    List<Combat> findByStatus(CombatStatus status);

    Optional<Combat> findTopByGameIdAndStatusOrderByIdDesc(Long gameId, CombatStatus status);

}
