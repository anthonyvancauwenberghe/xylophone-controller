import serial

default_port = 'COM7'
default_baud = 9600

ser = serial.Serial(default_port, default_baud)

def sendSequence(seq):
    ser.write(seq.encode())

sendSequence("0.17,2.16,-1.12,300;")

