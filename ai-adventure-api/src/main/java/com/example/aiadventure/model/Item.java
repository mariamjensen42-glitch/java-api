package com.example.aiadventure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String type;

    private Integer attackBonus;

    private Integer defenseBonus;

    private Integer healthBonus;

    @Column(nullable = false)
    private Boolean equipped = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_character_id", nullable = false)
    private PlayerCharacter playerCharacter;

}
