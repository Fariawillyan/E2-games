package e2.games.epocaepicamotor.dao;

import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.entity.EntityDefender;
import e2.games.epocaepicamotor.entity.EntityLootWar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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


    public void updateCharactersWhenVictoryDao(String nameAttacker) {
        String table = "tbcharacters";
        jdbcClient.sql("UPDATE " + table + " SET victory = victory + 1 WHERE name= :name")
                .param("name", nameAttacker)
                .update();
    }

    public void updateCharactersWhenDefeatedDao(String nameDefender) {
        String table = "tbcharacters";
        jdbcClient.sql("UPDATE " + table + " SET defeat = defeat + 1 WHERE name= :name")
                .param("name", nameDefender)
                .update();
    }

    public void createWarTransactionDao(EntityAttacker EntityAttacker) {
        String table = "tbwartransactionattack";
        jdbcClient.sql("INSERT INTO " + table + "(type, name_attack, health, attack_power, defense_power, army, name_defense) values(?, ?, ?, ?, ?, ?, ?)")
                .params(List.of(EntityAttacker.getType(),
                        EntityAttacker.getNameAttack(),
                        EntityAttacker.getHealth(),
                        EntityAttacker.getAttackPower(),
                        EntityAttacker.getDefensePower(),
                        EntityAttacker.getArmy(),
                        EntityAttacker.getNameDefense()))
                .update();
    }

    public void createWarResult(String attacker, String defender, String winner) {
        String table = "tbwarresult";
        jdbcClient.sql("INSERT INTO " + table + " (name_attack, name_defense, winner) values (:name_attack, :name_defense, :winner)")
                .param("name_attack", attacker)
                .param("name_defense", defender)
                .param("winner", winner)
                .update();
    }

}
