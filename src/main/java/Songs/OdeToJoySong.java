package Songs;

import Enums.Key;
import Enums.NoteLength;
import Sequences.SequenceChain;
import Sequences.Song;

public class OdeToJoySong extends Song {

    @Override
    protected void build(SequenceChain chain) {
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);

        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.THIRD);
        chain.addNote(Key.D, NoteLength.EIGHT);
        chain.addNote(Key.D, NoteLength.HALF);

        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);

        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.THIRD);
        chain.addNote(Key.C_LOW, NoteLength.EIGHT);
        chain.addNote(Key.C_LOW, NoteLength.HALF);

        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);

        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);

        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.HALF);

        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.QUARTER);

        chain.addNote(Key.G, NoteLength.QUARTER);
        chain.addNote(Key.F, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);

        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.D, NoteLength.THIRD);
        chain.addNote(Key.C_LOW, NoteLength.EIGHT);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
    }

    @Override
    protected int defaultTempo() {
        return 100 ;
    }
}
