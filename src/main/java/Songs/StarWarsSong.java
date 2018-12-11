package Songs;

import Enums.Key;
import Enums.NoteLength;
import Player.RobotKeyPlayer;
import Sequences.Sequence;
import Sequences.SequenceChain;
import Sequences.Song;

public class StarWarsSong extends Song {

    @Override
    protected void build(SequenceChain chain) {
        chain.addNote(Key.C_LOW, NoteLength.HALF);
        chain.addNote(Key.G, NoteLength.HALF);

        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.D, NoteLength.EIGHT);
        chain.addNote(Key.C_HIGH, NoteLength.HALF);
        chain.addNote(Key.G, NoteLength.HALF);

        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.D, NoteLength.EIGHT);
        chain.addNote(Key.C_HIGH, NoteLength.HALF);
        chain.addNote(Key.G, NoteLength.HALF);

        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.EIGHT);
        chain.addNote(Key.F, NoteLength.EIGHT);
        chain.addNote(Key.D, NoteLength.EIGHT);
    }

    @Override
    protected int defaultTempo() {
        return 110;
    }
}
