package e2.games.epocaepicamotor.entity;

import lombok.Data;

@Data
public class EntityAttacker {
    private String nameAttack;
    private String type;
    private int health;
    private int attackPower;
    private int defensePower;
    private String army;
    private String nameDefense;

}
