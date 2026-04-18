package com.example.aiadventure.service;

import com.example.aiadventure.model.Game;
import java.util.List;

public interface GameService {

    Game createGame(Game game);

    Game getGameById(Long id);

    List<Game> getGamesByUserId(Long userId);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);

    Game processPlayerAction(Long gameId, String action);

}