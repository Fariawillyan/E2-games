package e2.game.service;

import e2.game.dao.IPainelWarProducer;
import e2.game.entity.PainelWarProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PainelWarProducerService {

    @Autowired
    IPainelWarProducer iPainelWarProducer;

    public boolean isSendBatteWarService(PainelWarProducer painelWarProducer) {

        return iPainelWarProducer.isSendWarProducerDao(painelWarProducer);

    }
}
