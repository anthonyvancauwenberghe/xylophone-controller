import serial
import time
import songs
from songs import Vector

#Todo
# speed limits test
# hit accuracy test
# range test
# simulation hook

default_port = '/dev/ttyACM0'
default_baud = 115200
ser_timeout = 10
ser = serial.Serial(default_port, default_baud)
serial_timeout = 2

BPM = 60
BPMS = BPM/60/1000

keypositions = [    Vector(-0.9,0),Vector(-0.6,0),Vector(-0.3,0),Vector(0,0),
                    Vector(0.3,0),Vector(0.6,0),Vector(0.9,0),Vector(1.2,0) ]

def sendSequence(seq):
    ser.write(seq.encode())
    printIncoming()

def printIncoming():
    a = ser_timeout
    while a>0:
        while ser.in_waiting > 0:
            print(ser.readline().strip().decode('ASCII'))
        time.sleep(0.1)
        a-=0.1

def getKeyPositions():
    #vision code here
    #keypositions = vision.getKeyPosArray()
    print("")

def songToSequence(song):
    seq = ""
    for note in song:
        seq+=(  str(keypositions[note.pitch].x)+","+
                str(keypositions[note.pitch].y)+","+
                str("{0:.0f}".format(note.length/BPMS))+";")
    return seq


def play_song(song):
    time.sleep(2)
    sendSequence(songToSequence(song))

def imitate():
    #sound detection code here
    #sequence = detection.getSequenceArray()
    #play_song(convertToNotes(sequence))
    print("")

#play_song(songs.STAR_WARS)




