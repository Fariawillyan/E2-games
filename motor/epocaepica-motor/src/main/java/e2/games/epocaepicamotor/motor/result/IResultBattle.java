package e2.games.epocaepicamotor.motor.result;

import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.entity.EntityDefender;
import org.springframework.stereotype.Component;

public interface IResultBattle {

    void lootOfWarResult(EntityDefender entityDefender, EntityAttacker entityAttacker, String flag);

}
