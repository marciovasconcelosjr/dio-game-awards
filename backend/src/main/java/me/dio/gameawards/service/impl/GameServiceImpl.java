package me.dio.gameawards.service.impl;

import me.dio.gameawards.domain.model.Game;
import me.dio.gameawards.domain.repository.GameRepository;
import me.dio.gameawards.exception.BusinessException;
import me.dio.gameawards.exception.NoContentException;
import me.dio.gameawards.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;
    @Override
    public List<Game> findAll() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> gameById = gameRepository.findById(id);
        return gameById.orElseThrow(() -> new NoContentException());
    }

    @Override
    public void insert(Game game) {
        gameRepository.save(game);
    }

    @Override
    public void update(Long id, Game game) {
        Game gameDb = findById(id);
        if (gameDb.getId().equals(game.getId())) {
            gameRepository.save(game);
        } else {
            throw new BusinessException("Os IDs para alteração são divergentes.");
        }
    }

    @Override
    public void delete(Long id) {
        Game gameDb = findById(id);
        gameRepository.delete(gameDb);
    }

    @Override
    public void vote(Long id) {
        Game gameDb = findById(id);
        gameDb.setVotes(gameDb.getVotes() + 1);
        update(id, gameDb);
    }
}
