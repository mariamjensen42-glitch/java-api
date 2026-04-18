package com.example.aiadventure.service;

import com.example.aiadventure.model.*;
import com.example.aiadventure.repository.CombatRepository;
import com.example.aiadventure.repository.GameRepository;
import com.example.aiadventure.repository.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CombatServiceImpl implements CombatService {

    @Autowired
    private CombatRepository combatRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Combat startCombat(Long gameId, String enemyName, Integer enemyHealth, Integer enemyAttack, Integer enemyDefense) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null) {
            return null;
        }

        Combat combat = new Combat();
        combat.setGame(game);
        combat.setStatus(CombatStatus.IN_PROGRESS);
        combat.setCurrentTurn(1);
        combat.setEnemyName(enemyName);
        combat.setEnemyHealth(enemyHealth);
        combat.setEnemyMaxHealth(enemyHealth);
        combat.setEnemyAttack(enemyAttack);
        combat.setEnemyDefense(enemyDefense);

        String log = String.format("[%s] 战斗开始！玩家 VS %s (HP: %d, ATK: %d, DEF: %d)",
                LocalDateTime.now().format(FORMATTER),
                enemyName,
                enemyHealth,
                enemyAttack,
                enemyDefense);
        combat.setCombatLog(log);

        return combatRepository.save(combat);
    }

    @Override
    public Combat getCombat(Long id) {
        return combatRepository.findById(id).orElse(null);
    }

    @Override
    public Combat getCombatByGameId(Long gameId) {
        return combatRepository.findTopByGameIdAndStatusOrderByIdDesc(gameId, CombatStatus.IN_PROGRESS).orElse(null);
    }

    @Override
    public Combat playerAttack(Long combatId) {
        Combat combat = combatRepository.findById(combatId).orElse(null);
        if (combat == null || !combat.getStatus().equals(CombatStatus.IN_PROGRESS)) {
            return combat;
        }

        List<PlayerCharacter> playerCharacters = playerCharacterRepository.findByGameId(combat.getGame().getId());
        if (playerCharacters.isEmpty()) {
            return combat;
        }

        PlayerCharacter player = playerCharacters.get(0);

        Integer damage = calculateDamage(player.getAttack(), combat.getEnemyDefense());
        combat.setEnemyHealth(Math.max(0, combat.getEnemyHealth() - damage));

        String log = combat.getCombatLog();
        String newLog = String.format("\n[%s] 回合 %d - 玩家攻击！对 %s 造成 %d 点伤害。敌人剩余 HP: %d",
                LocalDateTime.now().format(FORMATTER),
                combat.getCurrentTurn(),
                combat.getEnemyName(),
                damage,
                combat.getEnemyHealth());
        combat.setCombatLog(log + newLog);

        combat = combatRepository.save(combat);
        return checkCombatEnd(combatId);
    }

    @Override
    public Combat enemyTurn(Long combatId) {
        Combat combat = combatRepository.findById(combatId).orElse(null);
        if (combat == null || !combat.getStatus().equals(CombatStatus.IN_PROGRESS)) {
            return combat;
        }

        List<PlayerCharacter> playerCharacters = playerCharacterRepository.findByGameId(combat.getGame().getId());
        if (playerCharacters.isEmpty()) {
            return combat;
        }

        PlayerCharacter player = playerCharacters.get(0);

        Integer damage = calculateDamage(combat.getEnemyAttack(), player.getDefense());
        player.setHealth(Math.max(0, player.getHealth() - damage));
        playerCharacterRepository.save(player);

        String log = combat.getCombatLog();
        String newLog = String.format("\n[%s] 回合 %d - %s 攻击！对玩家造成 %d 点伤害。玩家剩余 HP: %d",
                LocalDateTime.now().format(FORMATTER),
                combat.getCurrentTurn(),
                combat.getEnemyName(),
                damage,
                player.getHealth());
        combat.setCombatLog(log + newLog);
        combat.setCurrentTurn(combat.getCurrentTurn() + 1);

        combat = combatRepository.save(combat);
        return checkCombatEnd(combatId);
    }

    @Override
    public Integer calculateDamage(Integer attackerAttack, Integer defenderDefense) {
        int damage = attackerAttack - defenderDefense;
        return Math.max(1, damage);
    }

    @Override
    public Combat checkCombatEnd(Long combatId) {
        Combat combat = combatRepository.findById(combatId).orElse(null);
        if (combat == null || !combat.getStatus().equals(CombatStatus.IN_PROGRESS)) {
            return combat;
        }

        List<PlayerCharacter> playerCharacters = playerCharacterRepository.findByGameId(combat.getGame().getId());
        if (playerCharacters.isEmpty()) {
            return combat;
        }

        PlayerCharacter player = playerCharacters.get(0);
        String log = combat.getCombatLog();

        if (combat.getEnemyHealth() <= 0) {
            combat.setStatus(CombatStatus.PLAYER_WON);
            String winLog = String.format("\n[%s] 战斗结束！玩家获胜！",
                    LocalDateTime.now().format(FORMATTER));
            combat.setCombatLog(log + winLog);
        } else if (player.getHealth() <= 0) {
            combat.setStatus(CombatStatus.ENEMY_WON);
            String loseLog = String.format("\n[%s] 战斗结束！%s 获胜！",
                    LocalDateTime.now().format(FORMATTER),
                    combat.getEnemyName());
            combat.setCombatLog(log + loseLog);
        }

        return combatRepository.save(combat);
    }

    @Override
    public String getCombatLog(Long combatId) {
        Combat combat = combatRepository.findById(combatId).orElse(null);
        if (combat != null) {
            return combat.getCombatLog();
        }
        return null;
    }
}
