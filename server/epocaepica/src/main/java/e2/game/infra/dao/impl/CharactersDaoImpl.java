package e2.game.infra.dao.impl;

import e2.game.infra.dao.CharactersDao;
import e2.game.domain.Characters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CharactersDaoImpl implements CharactersDao {

    @Autowired
    JdbcClient jdbcClient;

    @Override
    public Optional<Characters> loadCharacterDao(String name) {
        return jdbcClient
                .sql("SELECT * from tbcharacters ch where ch.name = :name ")
                .param("name", name)
                .query(Characters.class)
                .optional();
    }

    @Override
    public boolean isCreateClassDao(Characters characters) {
        String table = "tbcharacters";
        return jdbcClient.sql("INSERT INTO " + table + "(type, name, health, attack_power, defense_power, army) values(?, ?, ?, ?, ?, ?)")
                .params(List.of(characters.type(), characters.name(), characters.health(), characters.attackPower(), characters.defensePower(), characters.army()))
                .update() > 0;
    }


}
