package e2.games.epocaepicamotor.motor;


import e2.games.epocaepicamotor.dao.PainelWarDao;
import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.entity.EntityDefender;
import e2.games.epocaepicamotor.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MotorBattle {

    @Autowired
    PainelWarDao painelWarDao;

    public void calculate(EntityAttacker entityAttacker) {
        final String nameAttack = entityAttacker.getNameAttack();

        EntityDefender entityDefender = loadDefenderPlayer(entityAttacker.getNameDefense());
        entityDefender.setNameDefender(entityAttacker.getNameDefense());

        log.info("MESSAGE IN DB DEFENDER : " + entityDefender);

        final int result = getResultAttack(entityAttacker, entityDefender);
        entityDefender.setHealth(entityDefender.getHealth() - result);
        final String nameDefender = entityDefender.getNameDefender();
        String winner;

        if (entityDefender.getHealth() <= 0) {

            log.info("*************** RESULT BATTLE : " + " THE " + nameAttack + " IS VICTORY **************************");

            updateVictory(nameAttack);
            updateDefeated(nameDefender);
            winner = nameAttack;
        } else {

            log.info("*************** RESULT BATTLE : " + " THE " + nameAttack + " IS DEFEATED **************************");

            updateVictory(nameDefender);
            updateDefeated(nameAttack);
            winner = nameDefender;
        }

        createWarTransaction(entityAttacker);
        createWarResult(nameAttack, nameDefender, winner);
    }

    private static int getResultAttack(EntityAttacker entityAttacker, EntityDefender entityDefender) {
        int result = Math.max(0, entityAttacker.getAttackPower()) - entityDefender.getDefensePower();
        return result;
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

    private void updateDefeated(String defeated) {
        painelWarDao.updateCharactersWhenDefeatedDao(defeated);
    }
}


