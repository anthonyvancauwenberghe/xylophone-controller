package Sequences;

import Enums.Key;
import Enums.NoteLength;

import java.util.ArrayList;
import java.util.List;

public class SequenceChain implements Sequence {

    public List<Note> notes = new ArrayList<Note>();

    public void addNote(Key key, NoteLength noteLength, double velocity) {
        this.notes.add(new Note(key, noteLength, velocity));
    }

    public void addNote(Key key, NoteLength noteLength) {
        this.addNote(key, noteLength, 1);
    }
}
