package e2.games.epocaepicamotor.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import e2.games.epocaepicamotor.entity.EntityAttacker;
import e2.games.epocaepicamotor.motor.MotorBattle;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PainelWarConsumer {

    @Autowired
    MotorBattle motorBattle;

    @RabbitListener(queues = "${spring.rabbit.queue.attack}")
    private void consumer(String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        EntityAttacker entityAttacker = objectMapper.readValue(json, EntityAttacker.class);

        log.info( "MESSAGE IN CONSUMER : " + entityAttacker);

        try{

            motorBattle.calculate(entityAttacker);

        }catch (Exception e){
            log.info("UNPROCESSED BATTLE FOR ATTACKING", e);
        }

    }



}
