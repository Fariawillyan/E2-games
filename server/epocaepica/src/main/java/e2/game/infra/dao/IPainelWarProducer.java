package e2.game.infra.dao;

import e2.game.domain.painelwar.PainelWarProducer;
import org.springframework.stereotype.Repository;

@Repository
public interface IPainelWarProducer {


    boolean isSendWarProducerDao(PainelWarProducer painelWarProducer);


}
