package e2.games.epocaepicamotor.motor.result;

import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.entity.EntityDefender;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResultOfVictoryImpl implements IResultBattle {

    @Override
    public void bootyOfWarResult(EntityDefender entityDefender, EntityAttacker entityAttacker) {


        //to implement loots

        log.info("*************** LOOT GAINED IN BATTLE: " + " THE " + entityDefender.getAttackPower() + " IS DEFEAT ************************");


    }
}
