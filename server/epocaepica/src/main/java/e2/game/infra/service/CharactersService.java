package e2.game.infra.service;

import e2.game.infra.dao.CharactersDao;
import e2.game.domain.Characters;
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

    public boolean isCreateClassService(Characters characters){
        return charactersDao.isCreateClassDao(characters);
    }


}

