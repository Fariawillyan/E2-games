package e2.game.service;

import e2.game.dao.CharactersDao;
import e2.game.entity.Characters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CharactersService {

    @Autowired(required = false)
    CharactersDao charactersDao;


    public Optional<Characters> loadCharacterService(String name) {
        return charactersDao.loadCharacterDao(name);
    }

    public boolean createClass(Characters characters){
        return charactersDao.createClass(characters);
    }


}

