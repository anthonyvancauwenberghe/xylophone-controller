package Sequences;

import Enums.Key;
import Enums.NoteLength;

public interface Sequence {
    public void addNote(Key key, NoteLength noteLength, double velocity);
}
