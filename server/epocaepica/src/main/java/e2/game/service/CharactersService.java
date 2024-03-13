package e2.game.service;

import e2.game.dao.CharactersDao;
import e2.game.entity.Characters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CharactersService<T> {

    @Autowired(required = false)
    CharactersDao charactersDao;

    public Optional<String> existNameActive(String name) {
        return charactersDao.existNameActiveDao(name);
    }

    public boolean createClass(Characters characters){
        return charactersDao.createClass(characters);
    }
}

