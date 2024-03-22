package e2.games.epocaepicamotor.dao;

import e2.games.epocaepicamotor.entity.EntityLootWar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LootWarDao {

    @Autowired
    JdbcClient jdbcClient;

    public Optional<EntityLootWar> loadLootDao(String nameDefense) {
        return jdbcClient
                .sql("SELECT * from tblootwar where name = :name ")
                .param("name", nameDefense)
                .query(EntityLootWar.class)
                .optional();
    }

    public void updateLootWinner(EntityLootWar entityLootWar, String winner) {
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
                .param("jewelOfSoul", entityLootWar.getJewelOfSoul())
                .param("jewelOfBless", entityLootWar.getJewelOfBless())
                .param("gold", entityLootWar.getGold())
                .param("silver", entityLootWar.getSilver())
                .param("bronze", entityLootWar.getBronze())
                .param("wood", entityLootWar.getWood())
                .param("stone", entityLootWar.getStone())
                .param("iron", entityLootWar.getIron())
                .param("metal", entityLootWar.getMetal())
                .param("ore", entityLootWar.getOre())
                .update();
    }


    public void updateLootLoserAndSubInQuery(EntityLootWar entityLootWar, String loser) {

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
                .param("gold", entityLootWar.getGold())
                .param("silver", entityLootWar.getSilver())
                .param("bronze", entityLootWar.getBronze())
                .param("wood", entityLootWar.getWood())
                .param("stone", entityLootWar.getStone())
                .param("iron", entityLootWar.getIron())
                .param("metal", entityLootWar.getMetal())
                .param("ore", entityLootWar.getOre())
                .update();

    }
}
