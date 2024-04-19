package e2.game.infra.service;

import e2.game.infra.dao.IPainelWarProducer;
import e2.game.domain.painelwar.PainelWarProducer;
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
