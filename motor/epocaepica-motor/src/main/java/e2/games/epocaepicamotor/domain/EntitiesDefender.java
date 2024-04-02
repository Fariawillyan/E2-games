package e2.games.epocaepicamotor.domain;


import java.util.Objects;

public class EntitiesDefender {
    private String nameDefender;
    private String type;
    private int health;
    private int attackPower;
    private int defensePower;
    private String army;
    private String nameAttack;


    public String getNameDefender() {
        return nameDefender;
    }

    public void setNameDefender(String nameDefender) {
        this.nameDefender = nameDefender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    public String getArmy() {
        return army;
    }

    public void setArmy(String army) {
        this.army = army;
    }

    public String getNameAttack() {
        return nameAttack;
    }

    public void setNameAttack(String nameAttack) {
        this.nameAttack = nameAttack;
    }

    @Override
    public String toString() {
        return "EntityDefender{" +
                "nameDefender='" + nameDefender + '\'' +
                ", type='" + type + '\'' +
                ", health=" + health +
                ", attackPower=" + attackPower +
                ", defensePower=" + defensePower +
                ", army='" + army + '\'' +
                ", nameAttack='" + nameAttack + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntitiesDefender that = (EntitiesDefender) o;
        return health == that.health && attackPower == that.attackPower && defensePower == that.defensePower && Objects.equals(nameDefender, that.nameDefender) && Objects.equals(type, that.type) && Objects.equals(army, that.army) && Objects.equals(nameAttack, that.nameAttack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameDefender, type, health, attackPower, defensePower, army, nameAttack);
    }
}
