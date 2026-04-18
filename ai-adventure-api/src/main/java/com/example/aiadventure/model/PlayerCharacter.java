package com.example.aiadventure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "player_characters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer health;

    @Column(nullable = false)
    private Integer maxHealth;

    @Column(nullable = false)
    private Integer attack;

    @Column(nullable = false)
    private Integer defense;

    @Column(nullable = false)
    private Integer morality;

    @Column(nullable = false)
    private Integer knowledge;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @OneToMany(mappedBy = "playerCharacter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

    @OneToMany(mappedBy = "playerCharacter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Quest> quests;

}
