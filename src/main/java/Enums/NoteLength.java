package Enums;

public enum NoteLength {
    SIXTEENTH(1, 16),

    EIGHT(1,8),

    FIVE_EIGHT(5,8),

    QUARTER(1,4),

    THIRD(1,3),

    HALF(1,2),

    THREE_QUARTER(3,4),

    WHOLE(1,1);

    public final double length;

     NoteLength(double upper, double bottom) {
        this.length = upper / bottom;
    }
}
