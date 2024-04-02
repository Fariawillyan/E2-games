package e2.games.epocaepicamotor.application.gateways;

import e2.games.epocaepicamotor.domain.EntitiesAttacker;
import e2.games.epocaepicamotor.domain.EntitiesDefender;

public interface IResultBattle {

    void lootOfWarResult(EntitiesDefender entitiesDefender, EntitiesAttacker entitiesAttacker, String flag);

}
