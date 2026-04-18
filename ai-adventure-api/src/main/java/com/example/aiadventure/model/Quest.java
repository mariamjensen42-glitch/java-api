package com.example.aiadventure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestStatus status;

    @Column(nullable = false)
    private Integer progress = 0;

    @Column(nullable = false)
    private Integer targetProgress = 1;

    @Column(columnDefinition = "TEXT")
    private String reward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_character_id", nullable = false)
    private PlayerCharacter playerCharacter;

}
