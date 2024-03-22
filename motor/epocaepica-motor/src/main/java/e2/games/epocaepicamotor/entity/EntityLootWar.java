package e2.games.epocaepicamotor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityLootWar extends EntityDefender {


    private long jewelOfSoul;
    private long jewelOfBless;
    private long gold;
    private long silver;
    private long bronze;
    private long wood;
    private long stone;
    private long iron;
    private long metal;
    private long ore;
}
