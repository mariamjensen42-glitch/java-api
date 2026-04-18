package com.example.aiadventure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "save_games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer slotNumber;

    @Column(nullable = false)
    private String saveName;

    @Column(nullable = false)
    private LocalDateTime saveTime;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String gameState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @PrePersist
    protected void onCreate() {
        saveTime = LocalDateTime.now();
    }

}
