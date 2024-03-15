package e2.game.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import e2.game.entity.PainelWarProducer;
import e2.game.exception.CustomException;
import e2.game.service.PainelWarProducerService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/painel-war")
@Log4j2
public class PainelWarProducerController {

    @Autowired(required = false)
    PainelWarProducerService painelWarProducerService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/send-battle")
    public ResponseEntity<Object> sendBattleWar(@RequestBody PainelWarProducer painelWarProducer){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(painelWarProducer);

            rabbitTemplate.convertAndSend("battlewarattack", json);

        } catch (AmqpException e) {
            log.debug("Failed to send message to RabbitMQ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message to RabbitMQ");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        final boolean battleSendDb = painelWarProducerService.sendBatteWarService(painelWarProducer);

        return battleSendDb ?
                ResponseEntity.status(HttpStatus.OK).body("WAR IN PROGRESS! ") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("Failed WAR"));
    }


}
