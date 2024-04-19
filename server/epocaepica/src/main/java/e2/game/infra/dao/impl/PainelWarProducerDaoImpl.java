package e2.game.infra.dao.impl;

import e2.game.infra.dao.IPainelWarProducer;
import e2.game.domain.painelwar.PainelWarProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
public class PainelWarProducerDaoImpl implements IPainelWarProducer {

    @Autowired
    JdbcClient jdbcClient;

    @Override
    public boolean isSendWarProducerDao(PainelWarProducer painelWarProducer) {
        String table = "tbwartransactionattack";
        return jdbcClient.sql("INSERT INTO " + table + "(name_attack, type, health, attack_power, defense_power, army, name_defense) values(?, ?, ?, ?, ?, ?, ?)")
                .params(
                        painelWarProducer.nameAttack(),
                        painelWarProducer.type(),
                        painelWarProducer.health(),
                        painelWarProducer.attackPower(),
                        painelWarProducer.defensePower(),
                        painelWarProducer.army(),
                        painelWarProducer.nameDefense()
                )
                .update() > 0;
    }


}
