package e2.games.epocaepicamotor.infra.persistence;

import e2.games.epocaepicamotor.domain.VoLootWar;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.Optional;

public class LootWarDao {

    private final JdbcClient jdbcClient;

    public LootWarDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<VoLootWar> loadLootDao(String nameDefense) {
        return jdbcClient
                .sql("SELECT * from tblootwar where name = :name ")
                .param("name", nameDefense)
                .query(VoLootWar.class)
                .optional();
    }

    public void updateLootWinner(VoLootWar voLootWar, String winner) {
        String table = "tblootwar";
        jdbcClient.sql("UPDATE " + table + " SET " +
                        "jewelOfSoul = jewelOfSoul + 1, " +
                        "jewelOfBless = jewelOfBless + 2, " +
                        "gold = gold + :gold, " +
                        "silver = silver + :silver, " +
                        "bronze = bronze + :bronze, " +
                        "wood = wood + :wood, " +
                        "stone = stone + :stone, " +
                        "iron = iron + :iron, " +
                        "metal = metal + :metal, " +
                        "ore = ore + :ore " +
                        "WHERE name = :name ")
                .param("name", winner)
                .param("jewelOfSoul", voLootWar.getJewelOfSoul())
                .param("jewelOfBless", voLootWar.getJewelOfBless())
                .param("gold", voLootWar.getGold())
                .param("silver", voLootWar.getSilver())
                .param("bronze", voLootWar.getBronze())
                .param("wood", voLootWar.getWood())
                .param("stone", voLootWar.getStone())
                .param("iron", voLootWar.getIron())
                .param("metal", voLootWar.getMetal())
                .param("ore", voLootWar.getOre())
                .update();
    }


    public void updateLootLoserAndSubInQuery(VoLootWar voLootWar, String loser) {

        String table = "tblootwar";
        jdbcClient.sql("UPDATE " + table + " SET " +
                        "gold = gold - :gold, " +
                        "silver = silver - :silver, " +
                        "bronze = bronze - :bronze, " +
                        "wood = wood - :wood, " +
                        "stone = stone - :stone, " +
                        "iron = iron - :iron, " +
                        "metal = metal - :metal, " +
                        "ore = ore - :ore " +
                        "WHERE name = :name ")
                .param("name", loser)
                .param("gold", voLootWar.getGold())
                .param("silver", voLootWar.getSilver())
                .param("bronze", voLootWar.getBronze())
                .param("wood", voLootWar.getWood())
                .param("stone", voLootWar.getStone())
                .param("iron", voLootWar.getIron())
                .param("metal", voLootWar.getMetal())
                .param("ore", voLootWar.getOre())
                .update();

    }
}
