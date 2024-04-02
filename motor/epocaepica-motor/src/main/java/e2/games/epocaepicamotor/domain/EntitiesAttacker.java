package e2.games.epocaepicamotor.domain;


import java.util.Objects;

public class EntitiesAttacker {
    private String nameAttack;
    private String type;
    private int health;
    private int attackPower;
    private int defensePower;
    private String army;
    private String nameDefense;

    public String getNameAttack() {
        return nameAttack;
    }

    public void setNameAttack(String nameAttack) {
        this.nameAttack = nameAttack;
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

    public String getNameDefense() {
        return nameDefense;
    }

    public void setNameDefense(String nameDefense) {
        this.nameDefense = nameDefense;
    }

    @Override
    public String toString() {
        return "EntityAttacker{" +
                "nameAttack='" + nameAttack + '\'' +
                ", type='" + type + '\'' +
                ", health=" + health +
                ", attackPower=" + attackPower +
                ", defensePower=" + defensePower +
                ", army='" + army + '\'' +
                ", nameDefense='" + nameDefense + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntitiesAttacker that = (EntitiesAttacker) o;
        return health == that.health && attackPower == that.attackPower && defensePower == that.defensePower && Objects.equals(nameAttack, that.nameAttack) && Objects.equals(type, that.type) && Objects.equals(army, that.army) && Objects.equals(nameDefense, that.nameDefense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameAttack, type, health, attackPower, defensePower, army, nameDefense);
    }
}
