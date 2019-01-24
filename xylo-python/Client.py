import socket
import sys

HOST, PORT = "localhost", 9999

# Create a socket (SOCK_STREAM means a TCP socket)
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    # Connect to server
    sock.connect((HOST, PORT))

    # Receive data from the server and shut down
    received = sock.recv(1024)
finally:
    sock.close()

print "Received: {}".format(received)