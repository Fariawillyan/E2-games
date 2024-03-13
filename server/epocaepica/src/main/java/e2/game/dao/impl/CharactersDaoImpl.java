package e2.game.dao.impl;

import e2.game.dao.CharactersDao;
import e2.game.entity.Characters;
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
    public Optional<String> existNameActiveDao(Object name) {
        return jdbcClient
                .sql("SELECT 1 from tbcharacters where name = :name ")
                .param("name", name)
                .query(String.class)
                .optional();
    }

    @Override
    public boolean createClass(Characters characters) {
        String table = "tbcharacters";
        return jdbcClient.sql("INSERT INTO " + table + "(type, name, health, attack_power, defense_power, army) values(?, ?, ?, ?, ?, ?)")
                .params(List.of(characters.type(), characters.name(), characters.health(), characters.attackPower(), characters.defensePower(), characters.army()))
                .update() > 0;
    }
}
