package com.example.aiadventure.service;

import com.example.aiadventure.model.GameEvent;
import com.example.aiadventure.repository.GameEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameEventServiceImpl implements GameEventService {

    @Autowired
    private GameEventRepository gameEventRepository;

    @Override
    public GameEvent createGameEvent(GameEvent gameEvent) {
        return gameEventRepository.save(gameEvent);
    }

    @Override
    public GameEvent getGameEventById(Long id) {
        return gameEventRepository.findById(id).orElse(null);
    }

    @Override
    public List<GameEvent> getGameEventsByGameId(Long gameId) {
        return gameEventRepository.findByGameId(gameId);
    }

    @Override
    public void deleteGameEvent(Long id) {
        gameEventRepository.deleteById(id);
    }

}