package Songs;

import Enums.Key;
import Enums.NoteLength;
import Player.RobotKeyPlayer;
import Sequences.Sequence;
import Sequences.SequenceChain;
import Sequences.Song;

public class StarWars2Song extends Song {

    @Override
    protected void build(SequenceChain chain) {
        chain.addNote(Key.D, NoteLength.THIRD);
        chain.addNote(Key.D, NoteLength.THIRD);
        chain.addNote(Key.D, NoteLength.THIRD);

        chain.addNote(Key.G, NoteLength.HALF);
        chain.addNote(Key.D, NoteLength.HALF);
        chain.addNote(Key.E, NoteLength.EIGHT);

        chain.addNote(Key.C_LOW, NoteLength.QUARTER);
        chain.addNote(Key.G, NoteLength.EIGHT);
        chain.addNote(Key.E, NoteLength.EIGHT);
    }

    @Override
    protected int defaultTempo() {
        return 120;
    }
}
