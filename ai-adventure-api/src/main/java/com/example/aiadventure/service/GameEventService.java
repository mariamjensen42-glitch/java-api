package com.example.aiadventure.service;

import com.example.aiadventure.model.GameEvent;
import java.util.List;

public interface GameEventService {

    GameEvent createGameEvent(GameEvent gameEvent);

    GameEvent getGameEventById(Long id);

    List<GameEvent> getGameEventsByGameId(Long gameId);

    void deleteGameEvent(Long id);

}