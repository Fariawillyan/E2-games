package e2.games.epocaepicamotor.dao;

import e2.games.epocaepicamotor.entity.EntityDefender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PainelWarDao {

    @Autowired
    JdbcClient jdbcClient;

    public Optional<EntityDefender> loadCharacterDao(String nameDefense) {

        return jdbcClient
                .sql("SELECT * from tbcharacters ch where ch.name = :name ")
                .param("name", nameDefense)
                .query(EntityDefender.class)
                .optional();
    }

}
