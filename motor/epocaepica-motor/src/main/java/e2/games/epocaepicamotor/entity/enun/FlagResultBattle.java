package e2.games.epocaepicamotor.entity.enun;

public enum FlagResultBattle {

    W("WINNER"),
    L("LOSER");

    private final String flag;

    FlagResultBattle(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}
