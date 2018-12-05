package Songs;

import Enums.Key;
import Enums.NoteLength;
import Sequences.SequenceChain;
import Sequences.Song;

public class BrotherJacobSong extends Song {

    @Override
    protected void build(SequenceChain chain) {
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);

        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);

        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.HALF);

        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.HALF);

        chain.addNote(Key.G, NoteLength.EIGHT);
        chain.addNote(Key.A, NoteLength.EIGHT);
        chain.addNote(Key.G, NoteLength.EIGHT);
        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);

        chain.addNote(Key.G, NoteLength.EIGHT);
        chain.addNote(Key.A, NoteLength.EIGHT);
        chain.addNote(Key.G, NoteLength.EIGHT);
        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);

        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.HALF);

        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.HALF);
    }

    @Override
    protected int defaultTempo() {
        return 120;
    }
}
