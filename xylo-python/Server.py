import SocketServer
import cv2
import Utilities
from TrackingSystem import TrackingSystem

class MyTCPHandler(SocketServer.BaseRequestHandler):

    SHOW_IMAGE = True

    def getVisionData(self):
        trackingSystem = TrackingSystem()
        cap = cv2.VideoCapture(1)
        cap.set(3, int(1280))
        cap.set(4, int(720))
        # Load image, resize and convert color to HSV
        _, frame = cap.read()
        img_crop = frame       
        # Get C Keys position from the TrackingSystem
        cKeysPosition = trackingSystem.getCKeys(img_crop)
    
        if cKeysPosition:
            m, c = Utilities.calculateLineThroughPoints(cKeysPosition[1][0], cKeysPosition[1][1])
            img_crop = trackingSystem.drawCKeys(img_crop, cKeysPosition[0], cKeysPosition[1])
            img_crop = trackingSystem.drawPlane(img_crop, cKeysPosition[0])
            print('C Keys position is ', cKeysPosition[1])

        # Get stick position from the TrackingSystem
        stickPosition = trackingSystem.getStickPosition(img_crop)
        if stickPosition:
            img_crop = trackingSystem.drawStick(img_crop, stickPosition[0], stickPosition[1], stickPosition[2])
            print('Stick position is ', stickPosition[0], stickPosition[1], 'radius:', stickPosition[2])

        if self.SHOW_IMAGE:
            cv2.imshow('Output', img_crop)

        cap.release()
        cv2.destroyAllWindows()

        return cKeysPosition, m, c, stickPosition

    def handle(self):
        # self.request is the TCP socket connected to the client
        cKeysPosition, m, c, stickPosition = self.getVisionData()
        self.request.sendall("y = {m}x + {c}".format(m=m,c=c))

if __name__ == "__main__":
    HOST, PORT = "localhost", 9999

    # Create the server, binding to localhost on port 9999
    server = SocketServer.TCPServer((HOST, PORT), MyTCPHandler)

    # Activate the server; this will keep running until you
    # interrupt the program with Ctrl-C
    server.serve_forever()