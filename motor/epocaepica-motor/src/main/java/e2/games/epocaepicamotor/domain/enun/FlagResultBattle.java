package e2.games.epocaepicamotor.domain.enun;

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
