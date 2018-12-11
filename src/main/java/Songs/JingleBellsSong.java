package Songs;

import Enums.Key;
import Enums.NoteLength;
import Sequences.SequenceChain;
import Sequences.Song;

public class JingleBellsSong extends Song {

    @Override
    protected void build(SequenceChain chain) {
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.QUARTER);

        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.QUARTER);
        chain.addNote(Key.E, NoteLength.EIGHT);

        chain.addNote(Key.G, NoteLength.EIGHT);
        chain.addNote(Key.C_LOW, NoteLength.EIGHT);
        chain.addNote(Key.D, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.HALF);

        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.F, NoteLength.QUARTER);

        chain.addNote(Key.F, NoteLength.SIXTEENTH);
        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.SIXTEENTH);
        chain.addNote(Key.E, NoteLength.SIXTEENTH);
        chain.addNote(Key.G, NoteLength.EIGHT);
        chain.addNote(Key.G, NoteLength.EIGHT);
        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.D, NoteLength.EIGHT);
        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
    }

    @Override
    protected int defaultTempo() {
        return 140 ;
    }
}
