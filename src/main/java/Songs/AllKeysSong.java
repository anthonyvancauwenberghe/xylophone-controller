package Songs;

import Enums.Key;
import Enums.NoteLength;
import Sequences.SequenceChain;
import Sequences.Song;

public class AllKeysSong extends Song {

    @Override
    protected void build(SequenceChain chain) {
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.QUARTER);
        chain.addNote(Key.A, NoteLength.QUARTER);
        chain.addNote(Key.B, NoteLength.QUARTER);
        chain.addNote(Key.C_HIGH, NoteLength.QUARTER);
    }

    @Override
    protected int defaultTempo() {
        return 150;
    }
}
