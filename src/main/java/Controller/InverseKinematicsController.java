package Controller;

import Arduino.ArduinoSerialManager;
import Sequences.Note;
import Sequences.Song;

public class InverseKinematicsController {
    protected ArduinoSerialManager serialManager;

    public Song song;

    public InverseKinematicsController(Song song) {
        this.serialManager = ArduinoSerialManager.getInstance();
        this.song = song;
    }

    public void send() {
        String sequence = "";
        for (Note note : song.getNotes()) {
            sequence = sequence + calculateKeyXPosition(note) + "," + calculateKeyYPosition(note) + "," + calculateNoteDelay(note) + ";";
        }
        System.out.println(sequence);
        this.serialManager.write(sequence);
    }

    protected final double calculateKeyXPosition(Note note) {
        switch (note.key.type) {
            case "A":
                return 0.56;
            case "B":
                return 0.87;
            case "C0":
                return -0.87;
            case "C1":
                return 1.16;
            case "D":
                return -0.58;
            case "E":
                return -0.29;
            case "F":
                return 0.0;
            case "G":
                return 0.29;
            default:
                throw new RuntimeException("key does not exist on player");

        }
    }

    protected final double calculateKeyYPosition(Note note) {
        switch (note.key.type) {
            case "A":
                return 0.0;
            case "B":
                return 0.0;
            case "C0":
                return 0.0;
            case "C1":
                return 0.0;
            case "D":
                return 0.0;
            case "E":
                return 0.0;
            case "F":
                return 0.0;
            case "G":
                return 0.0;
            default:
                throw new RuntimeException("key does not exist on player");

        }
    }

    protected long calculateNoteDelay(Note note) {
        return (long) ((60000 / song.getTempo()) * 4 * note.length.length);
    }
}
