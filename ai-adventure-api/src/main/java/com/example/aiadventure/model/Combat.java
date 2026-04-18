package com.example.aiadventure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "combats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Combat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CombatStatus status;

    @Column(nullable = false)
    private Integer currentTurn;

    @Column(columnDefinition = "TEXT")
    private String combatLog;

    @Column(nullable = false)
    private String enemyName;

    @Column(nullable = false)
    private Integer enemyHealth;

    @Column(nullable = false)
    private Integer enemyMaxHealth;

    @Column(nullable = false)
    private Integer enemyAttack;

    @Column(nullable = false)
    private Integer enemyDefense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

}
