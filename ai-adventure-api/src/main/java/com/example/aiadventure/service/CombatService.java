package com.example.aiadventure.service;

import com.example.aiadventure.model.Combat;

public interface CombatService {

    Combat startCombat(Long gameId, String enemyName, Integer enemyHealth, Integer enemyAttack, Integer enemyDefense);

    Combat getCombat(Long id);

    Combat getCombatByGameId(Long gameId);

    Combat playerAttack(Long combatId);

    Combat enemyTurn(Long combatId);

    Integer calculateDamage(Integer attackerAttack, Integer defenderDefense);

    Combat checkCombatEnd(Long combatId);

    String getCombatLog(Long combatId);

}
