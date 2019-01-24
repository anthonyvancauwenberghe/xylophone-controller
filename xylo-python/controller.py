import serial
import time
import songs
from songs import Vector
import computervision
#Todo
# speed limits test
# hit accuracy test
# range test
# simulation hook

default_port = 'COM8'
default_baud = 115200
ser_timeout = 10
ser = serial.Serial(default_port, default_baud)
serial_timeout = 2

BPM = 90
BPMS = 60000 / BPM * 4

keypositions = [    Vector(-0.175-0.95,0),Vector(-0.175-0.6,0),Vector(-0.175-0.4,0),Vector(-0.175-0.13,0),
                     Vector(-0.175+0.13,0),Vector(-0.175+0.44,0),Vector(-0.175+0.74,0),Vector(-0.175+0.97,0) ]

#keypositions = [    Vector(-0.175-0.93,0),Vector(-0.175-0.74,0),Vector(-0.175-0.44,0),Vector(-0.175-0.13,0),
#                     Vector(-0.175+0.13,0),Vector(-0.175+0.44,0),Vector(-0.175+0.74,0),Vector(-0.175+0.93,0) ]

#keypositions = [Vector(-0.175+0.153,0.01),Vector(-0.175+0.15,0.02),Vector(-0.175+0.13,-0.02),Vector(-0.175+0.13,0.01),Vector(-0.175+0.14,0.005),Vector(-0.175+0.135,-0.01),Vector(-0.175+0.13,0),Vector(-0.175+0.13,0)]
#keypositions = []

for _ in range(0,8):
    keypositions.append(Vector(-1.1,0))

def sendSequence(seq):
    ser.write(seq.encode())
    printIncoming()

def printIncoming():
    a = ser_timeout
    while a>0:
        while ser.in_waiting > 0:
            try:
                print(ser.readline().strip().decode('ASCII'))
            except UnicodeDecodeError:
                print("")
        time.sleep(0.1)
        a-=0.1

def getKeyPositions():
    #vision code here
    (setupCenterX, setupCenterY, setupM, setupC) = getSetupCenter()
    coordinates = getVisionData(setupCenterX, setupCenterY, setupM, setupC)
    #keypositions = vision.getKeyPosArray()
    print(coordinates)

def getTravelCorr(x):
    return -x*0.025

def songToSequence(song):
    seq = ""
    i = 0
    prev = None
    for note in song:
        if i != 0:
            corr = getTravelCorr(keypositions[note.pitch].x - prev)
            print(corr)
        else:
            corr = 0
        seq+=(  str(keypositions[note.pitch].x + corr)+","+
                str(keypositions[note.pitch].y)+","+
                str("{0:.0f}".format(note.length*BPMS))+";")
        prev = keypositions[note.pitch].x
        i+=1
    return seq

def recordingToSequence(song):
    seq = ""
    for note in song:
        seq+=(  str(keypositions[note.pitch].x)+","+
                str(keypositions[note.pitch].y)+","+
                str("{0:.0f}".format(note.length*6))+";")
    return seq

def play_song(song):
    time.sleep(2)
    sendSequence(songToSequence(song))

def play_recording(song):
    time.sleep(2)
    sendSequence(recordingToSequence(song))


def imitate():
    #sound detection code here
    #sequence = detection.getSequenceArray()
    #play_song(convertToNotes(sequence))
    print("")


getKeyPositions()
#play_recording(songs.DETECT)
#play_song(songs.ODE_TO_JOY)
#songToSequence(songs.SCALE2)




