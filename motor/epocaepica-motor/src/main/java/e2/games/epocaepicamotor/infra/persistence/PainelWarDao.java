package e2.games.epocaepicamotor.infra.persistence;

import e2.games.epocaepicamotor.domain.EntitiesAttacker;
import e2.games.epocaepicamotor.domain.EntitiesDefender;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;
import java.util.Optional;

public class PainelWarDao {

    private final JdbcClient jdbcClient;

    public PainelWarDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<EntitiesDefender> loadCharacterDao(String nameDefense) {
        return jdbcClient
                .sql("SELECT * from tbcharacters ch where ch.name = :name ")
                .param("name", nameDefense)
                .query(EntitiesDefender.class)
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

    public void createWarTransactionDao(EntitiesAttacker EntitiesAttacker) {
        String table = "tbwartransactionattack";
        jdbcClient.sql("INSERT INTO " + table + "(type, name_attack, health, attack_power, defense_power, army, name_defense) values(?, ?, ?, ?, ?, ?, ?)")
                .params(List.of(EntitiesAttacker.getType(),
                        EntitiesAttacker.getNameAttack(),
                        EntitiesAttacker.getHealth(),
                        EntitiesAttacker.getAttackPower(),
                        EntitiesAttacker.getDefensePower(),
                        EntitiesAttacker.getArmy(),
                        EntitiesAttacker.getNameDefense()))
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
