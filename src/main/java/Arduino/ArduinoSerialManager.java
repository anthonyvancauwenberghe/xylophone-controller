package Arduino;

import Configs.SerialConfig;
import arduino.Arduino;

public class ArduinoSerialManager {

    protected static ArduinoSerialManager instance;

    protected final Arduino arduino;

    private ArduinoSerialManager() {
        this.arduino = new Arduino(SerialConfig.PORT, SerialConfig.BAUD_RATE);
        this.boot();
    }


    protected void boot() {
        this.arduino.openConnection();
        if (!this.arduino.getSerialPort().isOpen()) {
            throw new RuntimeException("could not find or open connection");
        }
        this.listenForIncomingMessages();
    }

    protected void listenForIncomingMessages() {
        final Arduino arduino = this.arduino;
        Thread t = new Thread() {
            Arduino ard = arduino;

            public void run() {
                while (true) {
                    System.out.println(ard.serialRead(0));
                }
            }
        };
        t.start();
    }

    public String read() {
        return this.arduino.serialRead(0);
    }

    public void write(String message) {
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            this.arduino.serialWrite(c);
        }
        char nChar = '\n';
        this.arduino.serialWrite(nChar);
    }

    @Override
    public void finalize() {
        this.arduino.closeConnection();
    }

    public static ArduinoSerialManager getInstance() {
        if (instance == null)
            instance = new ArduinoSerialManager();
        return instance;
    }
}
