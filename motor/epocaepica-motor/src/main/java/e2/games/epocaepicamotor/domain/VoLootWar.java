package e2.games.epocaepicamotor.domain;

import java.util.Objects;

public class VoLootWar extends EntitiesDefender {

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

    public long getJewelOfSoul() {
        return jewelOfSoul;
    }

    public void setJewelOfSoul(long jewelOfSoul) {
        this.jewelOfSoul = jewelOfSoul;
    }

    public long getJewelOfBless() {
        return jewelOfBless;
    }

    public void setJewelOfBless(long jewelOfBless) {
        this.jewelOfBless = jewelOfBless;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getSilver() {
        return silver;
    }

    public void setSilver(long silver) {
        this.silver = silver;
    }

    public long getBronze() {
        return bronze;
    }

    public void setBronze(long bronze) {
        this.bronze = bronze;
    }

    public long getWood() {
        return wood;
    }

    public void setWood(long wood) {
        this.wood = wood;
    }

    public long getStone() {
        return stone;
    }

    public void setStone(long stone) {
        this.stone = stone;
    }

    public long getIron() {
        return iron;
    }

    public void setIron(long iron) {
        this.iron = iron;
    }

    public long getMetal() {
        return metal;
    }

    public void setMetal(long metal) {
        this.metal = metal;
    }

    public long getOre() {
        return ore;
    }

    public void setOre(long ore) {
        this.ore = ore;
    }

    @Override
    public String toString() {
        return "EntityLootWar{" +
                "jewelOfSoul=" + jewelOfSoul +
                ", jewelOfBless=" + jewelOfBless +
                ", gold=" + gold +
                ", silver=" + silver +
                ", bronze=" + bronze +
                ", wood=" + wood +
                ", stone=" + stone +
                ", iron=" + iron +
                ", metal=" + metal +
                ", ore=" + ore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoLootWar that = (VoLootWar) o;
        return jewelOfSoul == that.jewelOfSoul && jewelOfBless == that.jewelOfBless && gold == that.gold && silver == that.silver && bronze == that.bronze && wood == that.wood && stone == that.stone && iron == that.iron && metal == that.metal && ore == that.ore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jewelOfSoul, jewelOfBless, gold, silver, bronze, wood, stone, iron, metal, ore);
    }


}
