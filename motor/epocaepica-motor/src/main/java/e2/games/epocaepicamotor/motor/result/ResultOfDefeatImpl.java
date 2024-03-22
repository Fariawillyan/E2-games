package e2.games.epocaepicamotor.motor.result;

import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.entity.EntityDefender;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ResultOfDefeatImpl implements IResultBattle {

    @Override
    public void lootOfWarResult(EntityDefender entityDefender, EntityAttacker entityAttacker, String flag) {

        //to implements loots

        log.info("*************** LOOT GAINED IN BATTLE DEFEAT: " + " THE " + entityDefender.getDefensePower() + " IS DEFEAT ************************");

    }
}
