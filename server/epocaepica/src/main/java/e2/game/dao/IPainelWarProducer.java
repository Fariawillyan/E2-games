package e2.game.dao;

import e2.game.entity.PainelWarProducer;
import org.springframework.stereotype.Repository;

@Repository
public interface IPainelWarProducer {


    boolean sendWarProducerDao(PainelWarProducer painelWarProducer);


}
