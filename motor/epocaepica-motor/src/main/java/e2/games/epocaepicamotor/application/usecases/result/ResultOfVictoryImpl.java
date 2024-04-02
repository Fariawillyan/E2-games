package e2.games.epocaepicamotor.application.usecases.result;

import e2.games.epocaepicamotor.application.gateways.IResultBattle;
import e2.games.epocaepicamotor.infra.persistence.LootWarDao;
import e2.games.epocaepicamotor.domain.EntitiesAttacker;
import e2.games.epocaepicamotor.domain.EntitiesDefender;
import e2.games.epocaepicamotor.domain.VoLootWar;
import e2.games.epocaepicamotor.domain.exception.CustomException;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class ResultOfVictoryImpl implements IResultBattle {


    private final LootWarDao lootWarDao;

    public ResultOfVictoryImpl(LootWarDao lootWarDao) {
        this.lootWarDao = lootWarDao;
    }

    @Override
    public void lootOfWarResult(EntitiesDefender entitiesDefender, EntitiesAttacker entitiesAttacker, String flag) {

        String name = entitiesDefender.getNameDefender();
        VoLootWar voLootWar = loadDefenderLoot(name);

        if(flag.equalsIgnoreCase("WINNER")){

            voLootWar.setGold(Math.multiplyExact(voLootWar.getGold(), 30) / 100);
            voLootWar.setIron(Math.multiplyExact(voLootWar.getIron(), 30) / 100);
            voLootWar.setSilver(Math.multiplyExact(voLootWar.getSilver(), 40) / 100);
            voLootWar.setBronze(Math.multiplyExact(voLootWar.getBronze(), 40) / 100);
            voLootWar.setWood(Math.multiplyExact(voLootWar.getWood(), 50) / 100);
            voLootWar.setStone(Math.multiplyExact(voLootWar.getStone(), 50) / 100);
            voLootWar.setMetal(Math.multiplyExact(voLootWar.getMetal(), 50) / 100);
            voLootWar.setOre(Math.multiplyExact(voLootWar.getOre(), 30) / 100);

            lootWarDao.updateLootWinner(voLootWar, entitiesAttacker.getNameAttack());
            lootWarDao.updateLootLoserAndSubInQuery(voLootWar, entitiesDefender.getNameDefender());
        }

        log.info("-------------------------------- INITIALIZE A LOOT WITH SUCCESS ----------------------------------");
        log.info("*************** LOOT WINNER IN BATTLE : " + " THE " + voLootWar + " ************************");
        log.info("*************** LOOT LOSE IN BATTLE : " + " THE " + voLootWar + "  ************************");


    }

    private VoLootWar loadDefenderLoot(String nameDefender) {
        try {
            return lootWarDao.loadLootDao(nameDefender)
                    .orElseThrow(() -> new CustomException("name in loot " + nameDefender + " not found"));

        } catch (Exception e) {
            log.error("Failed to retrieve name in loot {}: {}", nameDefender, e.getMessage());
            throw new RuntimeException("Error in loot", e);
        }
    }
}
