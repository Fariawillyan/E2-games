package e2.games.epocaepicamotor.motor;


import e2.games.epocaepicamotor.dao.PainelWarDao;
import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.entity.EntityDefender;
import e2.games.epocaepicamotor.entity.enun.FlagResultBattle;
import e2.games.epocaepicamotor.exception.CustomException;
import e2.games.epocaepicamotor.motor.result.IResultBattle;
import e2.games.epocaepicamotor.motor.result.ResultOfDefeatImpl;
import e2.games.epocaepicamotor.motor.result.ResultOfVictoryImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MotorBattle {

    @Autowired
    PainelWarDao painelWarDao;

    @Autowired
    ResultOfVictoryImpl rv;

    public void calculate(EntityAttacker entityAttacker) {
        final String nameAttack = entityAttacker.getNameAttack();

        EntityDefender entityDefender = loadDefenderPlayer(entityAttacker.getNameDefense());

        entityDefender.setNameDefender(entityAttacker.getNameDefense());
        entityDefender.setNameAttack(entityAttacker.getNameAttack());

        log.info("MESSAGE IN ATTACK : " + entityAttacker);
        log.info("DEFENSE IN DB DEFENDER : " + entityDefender);

        final int result = getResultAttack(entityAttacker, entityDefender);
        entityDefender.setHealth(entityDefender.getHealth() - result);
        final String nameDefender = entityDefender.getNameDefender();
        String winner;
        String flag;

        if (entityDefender.getHealth() <= 0) {

            updateVictory(nameAttack);
            updateDefeat(nameDefender);

            flag = FlagResultBattle.W.getFlag();

            lootGenerator(rv, entityDefender, entityAttacker, flag);

            winner = nameAttack;
            log.info("*************** RESULT BATTLE : " + " THE " + nameAttack + " IS VICTORY **************************");

        } else {

            updateVictory(nameDefender);
            updateDefeat(nameAttack);

            flag = FlagResultBattle.L.getFlag();

            ResultOfDefeatImpl rd = new ResultOfDefeatImpl();

            winner = nameDefender;

            lootGenerator(rd, entityDefender, entityAttacker, flag);

            log.info("############### RESULT BATTLE : " + " THE " + nameAttack + " IS DEFEAT ######################");
        }

        createWarTransaction(entityAttacker);
        createWarResult(nameAttack, nameDefender, winner);
    }

    private static int getResultAttack(EntityAttacker entityAttacker, EntityDefender entityDefender) {
        return Math.max(0, entityAttacker.getAttackPower()) - entityDefender.getDefensePower();
    }

    private EntityDefender loadDefenderPlayer(String nameDefender) {
        try {
            return painelWarDao.loadCharacterDao(nameDefender)
                    .orElseThrow(() -> new CustomException("Defender with name " + nameDefender + " not found"));

        } catch (Exception e) {
            log.error("Failed to retrieve defender with name {}: {}", nameDefender, e.getMessage());
            throw new RuntimeException("Error retrieving defender", e);
        }
    }

    private void createWarTransaction(EntityAttacker entityAttacker) {
        painelWarDao.createWarTransactionDao(entityAttacker);
    }

    private void createWarResult(String attacker, String defender, String winner) {
        painelWarDao.createWarResult(attacker, defender, winner);
    }

    private void updateVictory(String victory) {
        painelWarDao.updateCharactersWhenVictoryDao(victory);
    }

    private void updateDefeat(String defeated) {
        painelWarDao.updateCharactersWhenDefeatedDao(defeated);
    }

    private static void lootGenerator(IResultBattle iResultBattle, EntityDefender entityDefender, EntityAttacker entityAttacker, String flag){
        iResultBattle.lootOfWarResult(entityDefender, entityAttacker, flag);
    }


}


