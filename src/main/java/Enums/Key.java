package Enums;

public enum Key {
    A("A"),
    B("B"),
    C_LOW("C0"),
    C_HIGH("C1"),
    D("D"),
    E("E"),
    F("F"),
    G("G");

    public final String type;

    private Key(String key){
        this.type = key;
    }
}
