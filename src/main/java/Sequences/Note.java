package Sequences;

import Enums.Key;
import Enums.NoteLength;

public final class Note {

    public final Key key;
    public final NoteLength length;
    public final double velocity;

    public Note(Key key, NoteLength length, double velocity) {
        this.key = key;
        this.length = length;
        this.velocity = velocity;
    }
}
