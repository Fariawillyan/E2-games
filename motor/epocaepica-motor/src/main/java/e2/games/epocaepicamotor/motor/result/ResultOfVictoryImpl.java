package e2.games.epocaepicamotor.motor.result;

import e2.games.epocaepicamotor.dao.LootWarDao;
import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.entity.EntityDefender;
import e2.games.epocaepicamotor.entity.EntityLootWar;
import e2.games.epocaepicamotor.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ResultOfVictoryImpl implements IResultBattle {


    @Autowired
    private LootWarDao lootWarDao;

    @Override
    public void lootOfWarResult(EntityDefender entityDefender, EntityAttacker entityAttacker, String flag) {

        String name = entityDefender.getNameDefender();
        EntityLootWar entityLootWar = loadDefenderLoot(name);

        if(flag.equalsIgnoreCase("WINNER")){

            entityLootWar.setGold(Math.multiplyExact(entityLootWar.getGold(), 30) / 100);
            entityLootWar.setIron(Math.multiplyExact(entityLootWar.getIron(), 30) / 100);
            entityLootWar.setSilver(Math.multiplyExact(entityLootWar.getSilver(), 40) / 100);
            entityLootWar.setBronze(Math.multiplyExact(entityLootWar.getBronze(), 40) / 100);
            entityLootWar.setWood(Math.multiplyExact(entityLootWar.getWood(), 50) / 100);
            entityLootWar.setStone(Math.multiplyExact(entityLootWar.getStone(), 50) / 100);
            entityLootWar.setMetal(Math.multiplyExact(entityLootWar.getMetal(), 50) / 100);
            entityLootWar.setOre(Math.multiplyExact(entityLootWar.getOre(), 30) / 100);

            lootWarDao.updateLootWinner(entityLootWar, entityAttacker.getNameAttack());
            lootWarDao.updateLootLoserAndSubInQuery(entityLootWar, entityDefender.getNameDefender());
        }

        log.info("-------------------------------- INITIALIZE A LOOT WITH SUCCESS ----------------------------------");
        log.info("*************** LOOT WINNER IN BATTLE : " + " THE " + entityLootWar + " ************************");
        log.info("*************** LOOT LOSE IN BATTLE : " + " THE " + entityLootWar + "  ************************");


    }

    private EntityLootWar loadDefenderLoot(String nameDefender) {
        try {
            return lootWarDao.loadLootDao(nameDefender)
                    .orElseThrow(() -> new CustomException("name in loot " + nameDefender + " not found"));

        } catch (Exception e) {
            log.error("Failed to retrieve name in loot {}: {}", nameDefender, e.getMessage());
            throw new RuntimeException("Error in loot", e);
        }
    }
}
