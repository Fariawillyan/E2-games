package e2.game.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import e2.game.domain.painelwar.PainelWarProducer;
import e2.game.application.exception.CustomException;
import e2.game.infra.service.PainelWarProducerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        final boolean isBattleSendDb = painelWarProducerService.isSendBatteWarService(painelWarProducer);

        return isBattleSendDb ?
                ResponseEntity.status(HttpStatus.OK).body("WAR IN PROGRESS! ") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("Failed WAR"));
    }


}
