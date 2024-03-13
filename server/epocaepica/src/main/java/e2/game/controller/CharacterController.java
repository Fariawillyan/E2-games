package e2.game.controller;

import e2.game.entity.Characters;
import e2.game.exception.CustomException;
import e2.game.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    CharactersService charactersService;

    @PostMapping("/create-player")
    public ResponseEntity<Object> createPlayer(@RequestBody Characters characters) {

        try {
            if (charactersService.existNameActive(characters.name()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomException("Name already exist! "));
            }

            return charactersService.createClass(characters) ? ResponseEntity.status(HttpStatus.CREATED).body("Player created with success! ") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("Failed to create player"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("Internal server error"));

        }
    }
}