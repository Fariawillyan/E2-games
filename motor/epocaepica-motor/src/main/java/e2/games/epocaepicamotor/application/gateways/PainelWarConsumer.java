package e2.games.epocaepicamotor.application.gateways;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import e2.games.epocaepicamotor.domain.EntitiesAttacker;
import e2.games.epocaepicamotor.application.usecases.motor.MotorBattle;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Log4j2
public class PainelWarConsumer {

    private final MotorBattle motorBattle;

    public PainelWarConsumer(MotorBattle motorBattle) {
        this.motorBattle = motorBattle;
    }

    @RabbitListener(queues = "${spring.rabbit.queue.attack}")
    private void consumer(String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        EntitiesAttacker entitiesAttacker = objectMapper.readValue(json, EntitiesAttacker.class);

        log.info( "-------------------------------- INICIALIZE A NEW BATTLE ---------------------------------- ");

        try{
            motorBattle.calculate(entitiesAttacker);
        }catch (Exception e){
            log.info("UNPROCESSED BATTLE FOR ATTACKING", e);
        }
    }
}
