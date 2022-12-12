package me.dio.gameawards.controller.games;

import me.dio.gameawards.controller.exceptions.BaseRestController;
import me.dio.gameawards.domain.model.Game;
import me.dio.gameawards.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController extends BaseRestController {
    @Autowired
    private GameService businessLayer;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> findAll() {
        return ResponseEntity.ok(businessLayer.findAll());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> findById(@PathVariable Long id) {
        return ResponseEntity.ok(businessLayer.findById(id));
    }

    @PostMapping("/games")
    public ResponseEntity<Void> create(@RequestBody Game game) {
        businessLayer.insert(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Game game) {
        businessLayer.update(id, game);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/games/{id}/vote")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        businessLayer.vote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        businessLayer.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
