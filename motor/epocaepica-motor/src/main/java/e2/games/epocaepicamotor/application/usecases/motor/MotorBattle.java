package e2.games.epocaepicamotor.application.usecases.motor;


import e2.games.epocaepicamotor.infra.persistence.PainelWarDao;
import e2.games.epocaepicamotor.domain.EntitiesAttacker;
import e2.games.epocaepicamotor.domain.EntitiesDefender;
import e2.games.epocaepicamotor.domain.enun.FlagResultBattle;
import e2.games.epocaepicamotor.domain.exception.CustomException;
import e2.games.epocaepicamotor.application.gateways.IResultBattle;
import e2.games.epocaepicamotor.application.usecases.result.ResultOfDefeatImpl;
import e2.games.epocaepicamotor.application.usecases.result.ResultOfVictoryImpl;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class MotorBattle {

    private final PainelWarDao painelWarDao;

    private final ResultOfVictoryImpl rv;

    public MotorBattle(PainelWarDao painelWarDao, ResultOfVictoryImpl rv) {
        this.painelWarDao = painelWarDao;
        this.rv = rv;
    }

    public void calculate(EntitiesAttacker entitiesAttacker) {
        final String nameAttack = entitiesAttacker.getNameAttack();

        EntitiesDefender entitiesDefender = loadDefenderPlayer(entitiesAttacker.getNameDefense());

        entitiesDefender.setNameDefender(entitiesAttacker.getNameDefense());
        entitiesDefender.setNameAttack(entitiesAttacker.getNameAttack());

        log.info("MESSAGE IN ATTACK : " + entitiesAttacker);
        log.info("DEFENSE IN DB DEFENDER : " + entitiesDefender);

        final int result = getResultAttack(entitiesAttacker, entitiesDefender);
        entitiesDefender.setHealth(entitiesDefender.getHealth() - result);
        final String nameDefender = entitiesDefender.getNameDefender();
        String winner;
        String flag;

        if (entitiesDefender.getHealth() <= 0) {

            updateVictory(nameAttack);
            updateDefeat(nameDefender);

            flag = FlagResultBattle.W.getFlag();

            lootGenerator(rv, entitiesDefender, entitiesAttacker, flag);

            winner = nameAttack;
            log.info("*************** RESULT BATTLE : " + " THE " + nameAttack + " IS VICTORY **************************");

        } else {

            updateVictory(nameDefender);
            updateDefeat(nameAttack);

            flag = FlagResultBattle.L.getFlag();

            ResultOfDefeatImpl rd = new ResultOfDefeatImpl();

            winner = nameDefender;

            lootGenerator(rd, entitiesDefender, entitiesAttacker, flag);

            log.info("############### RESULT BATTLE : " + " THE " + nameAttack + " IS DEFEAT ######################");
        }

        createWarTransaction(entitiesAttacker);
        createWarResult(nameAttack, nameDefender, winner);
    }

    private static int getResultAttack(EntitiesAttacker entitiesAttacker, EntitiesDefender entitiesDefender) {
        return Math.max(0, entitiesAttacker.getAttackPower()) - entitiesDefender.getDefensePower();
    }

    private EntitiesDefender loadDefenderPlayer(String nameDefender) {
        try {
            return painelWarDao.loadCharacterDao(nameDefender)
                    .orElseThrow(() -> new CustomException("Defender with name " + nameDefender + " not found"));

        } catch (Exception e) {
            log.error("Failed to retrieve defender with name {}: {}", nameDefender, e.getMessage());
            throw new RuntimeException("Error retrieving defender", e);
        }
    }

    private void createWarTransaction(EntitiesAttacker entitiesAttacker) {
        painelWarDao.createWarTransactionDao(entitiesAttacker);
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

    private static void lootGenerator(IResultBattle iResultBattle, EntitiesDefender entitiesDefender, EntitiesAttacker entitiesAttacker, String flag){
        iResultBattle.lootOfWarResult(entitiesDefender, entitiesAttacker, flag);
    }


}


