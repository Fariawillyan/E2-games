package e2.game.controller;

import e2.game.entity.PainelWarProducer;
import e2.game.exception.CustomException;
import e2.game.service.PainelWarProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/painel-war")
public class PainelWarProducerController {

    @Autowired(required = false)
    PainelWarProducerService painelWarProducerService;


    @PostMapping
    public ResponseEntity<Object> sendBattleWar(@RequestBody PainelWarProducer painelWarProducer){

        final boolean b = painelWarProducerService.sendBatteWarService(painelWarProducer);

        System.out.println(b);

        return b ?
                ResponseEntity.status(HttpStatus.OK).body("WAR IN PROGRESS! ") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("Failed WAR"));

    }





}
