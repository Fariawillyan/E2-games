package e2.game.controller;

import e2.game.entity.Characters;
import e2.game.exception.CustomException;
import e2.game.service.CharactersService;
import e2.game.service.ICharactersService;
import e2.game.service.impl.HumanServiceImpl;
import e2.game.service.impl.ZombieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    CharactersService charactersService;


    @PostMapping("/create-character")
    public ResponseEntity<Object> createCharacter(@RequestBody Characters characters) {

        try {
            if (charactersService.loadCharacterService(characters.name()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomException("Name already exist! "));
            }

            if("human".equalsIgnoreCase(characters.type())){
                bonusCharacter(new HumanServiceImpl());
            }else if("zombie".equalsIgnoreCase(characters.type())){
                bonusCharacter(new ZombieServiceImpl());
            }

            return charactersService.isCreateClassService(characters) ? ResponseEntity.status(HttpStatus.CREATED).body("Character created with success! ") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("Failed to create player"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("Internal server error"));

        }
    }

    @GetMapping("/load-character/{name}")
    public ResponseEntity<Object> loadCharacter(@PathVariable("name") String name){

        Optional<?> characters = charactersService.loadCharacterService(name);

        return  characters.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(characters) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    private static void bonusCharacter(ICharactersService iCharactersService){
        iCharactersService.BonusCharacters();
    }



}