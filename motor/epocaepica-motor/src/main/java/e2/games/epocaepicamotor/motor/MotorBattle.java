package e2.games.epocaepicamotor.motor;


import e2.games.epocaepicamotor.dao.PainelWarDao;
import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.entity.EntityDefender;
import e2.games.epocaepicamotor.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class MotorBattle {

    @Autowired
    PainelWarDao painelWarDao;


    public void calculate(EntityAttacker entityAttacker) {


        EntityDefender entityDefender = getDefenderName(entityAttacker.getNameDefense());
        entityDefender.setNameDefender(entityAttacker.getNameDefense());

        log.info( "MESSAGE IN DB DEFENDER : " + entityDefender);

        //... to be continue...

    }

    private EntityDefender getDefenderName(String nameDefender) {

        try {
            return painelWarDao.loadCharacterDao(nameDefender)
                    .orElseThrow(() -> new CustomException("Defender with name " + nameDefender + " not found"));

        } catch (Exception e) {
            log.error("Failed to retrieve defender with name {}: {}", nameDefender, e.getMessage());
            throw new RuntimeException("Error retrieving defender", e);
        }

    }


}
