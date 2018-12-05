package Sequences;

import java.util.List;

abstract public class Song {

    protected SequenceChain sequence;
    protected int tempo;

    public Song() {
        this.tempo = defaultTempo();
        this.sequence = new SequenceChain();
        this.build(this.sequence);
    }

    protected abstract void build(SequenceChain chain);

    protected abstract int defaultTempo();

    public List<Note> getNotes() {
        return this.sequence.notes;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getTempo() {
        return tempo;
    }
}
