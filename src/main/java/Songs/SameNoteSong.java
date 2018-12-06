package Songs;

import Enums.Key;
import Enums.NoteLength;
import Sequences.SequenceChain;
import Sequences.Song;

public class SameNoteSong extends Song {

    @Override
    protected void build(SequenceChain chain) {
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
    }

    @Override
    protected int defaultTempo() {
        return 200;
    }
}
