package e2.games.epocaepicamotor.application.usecases.result;

import e2.games.epocaepicamotor.application.gateways.IResultBattle;
import e2.games.epocaepicamotor.domain.EntitiesAttacker;
import e2.games.epocaepicamotor.domain.EntitiesDefender;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResultOfDefeatImpl implements IResultBattle {

    @Override
    public void lootOfWarResult(EntitiesDefender entitiesDefender, EntitiesAttacker entitiesAttacker, String flag) {

        //to implements loots

        log.info("*************** LOOT GAINED IN BATTLE DEFEAT: " + " THE " + entitiesDefender.getDefensePower() + " IS DEFEAT ************************");

    }
}
