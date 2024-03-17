package e2.game.dao;


import e2.game.entity.Characters;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharactersDao {


    Optional<Characters> loadCharacterDao(String name);

    boolean isCreateClassDao(Characters characters);
}
