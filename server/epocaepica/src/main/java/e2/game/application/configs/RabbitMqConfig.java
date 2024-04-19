package e2.game.application.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqConfig {

    private static final String NAME_EXCHANGE = "amq.direct";
    @Value("${spring.rabbit.queue.attack}")
    private String queueNameAttack;

    @Value("${spring.rabbit.queue.defense}")
    private String queueNameDefense;

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue queue(String queueName){
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange(){
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relationship(Queue queue, DirectExchange directExchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void addQueue(){

        Queue queueBattleAttack = this.queue(queueNameAttack);
        Queue queueBattleDefense = this.queue(queueNameDefense);

        Binding attack = this.relationship(queueBattleAttack, this.directExchange());
        Binding defense = relationship(queueBattleDefense, this.directExchange());

        this.amqpAdmin.declareQueue(queueBattleAttack);
        this.amqpAdmin.declareQueue(queueBattleDefense);

        this.amqpAdmin.declareExchange(this.directExchange());

        this.amqpAdmin.declareBinding(attack);
        this.amqpAdmin.declareBinding(defense);
    }
}
