package e2.games.epocaepicamotor.application.config;

import e2.games.epocaepicamotor.application.gateways.PainelWarConsumer;
import e2.games.epocaepicamotor.application.usecases.result.ResultOfVictoryImpl;
import e2.games.epocaepicamotor.application.usecases.motor.MotorBattle;
import e2.games.epocaepicamotor.infra.persistence.LootWarDao;
import e2.games.epocaepicamotor.infra.persistence.PainelWarDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
public class configBeans {

    @Bean
    public MotorBattle motorBattle(PainelWarDao painelWarDao, ResultOfVictoryImpl rv){
        return new MotorBattle(painelWarDao, rv);
    }

    @Bean
    public PainelWarDao painelWarDao(JdbcClient jdbcClient){
        return new PainelWarDao(jdbcClient);
    }

    @Bean
    public LootWarDao lootWarDao(JdbcClient jdbcClient){
        return new LootWarDao(jdbcClient);
    }

    @Bean
    public ResultOfVictoryImpl resultOfVictory(LootWarDao lootWarDao){
        return new ResultOfVictoryImpl(lootWarDao);
    }

    @Bean
    public PainelWarConsumer painelWarConsumer(MotorBattle motorBattle){
        return new PainelWarConsumer(motorBattle);
    }
}
