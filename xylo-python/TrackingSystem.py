from CircleTracker import CircleTracker
from KeysTracker import KeysTracker
from BorderTracker import BorderTracker
import cv2
import numpy as np
import Utilities
import math

class TrackingSystem:

    refKey = None
    cKeysPosition = None
    stickPosition = None
    borderPosition = None
    colors = ((0, 0, 255), (240, 0, 159), (0, 165, 255), (255, 255, 0),
	(255, 0, 255))

    def __init__(self):
        self.isFirstRun = True
        self.bordersTracker = BorderTracker()
        self.keysTracker = KeysTracker()
        self.circleTracker = CircleTracker()

    def trackCKeys(self, frame):
        boxes, centroids = self.keysTracker.getCKeysTracking(frame)

        return boxes, centroids

    def getCKeys(self, frame):
        boxes, centroids = self.trackCKeys(frame)
        centroids.sort()
        if self.cKeysPosition is not None:
            try:
                if Utilities.calculateDistance(centroids[0], self.cKeysPosition[1][0]) > 1 and \
                   Utilities.calculateDistance(centroids[1], self.cKeysPosition[1][1]) > 1 and \
                    len(centroids) < 3:
                    self.cKeysPosition = (boxes, centroids)
            except IndexError:
                return self.cKeysPosition
        else:
            self.cKeysPosition = (boxes, centroids)

        return self.cKeysPosition

    def getCoordinates(self, orig, angle, setupCenter):
        cX, cY = Utilities.midpoint(self.cKeysPosition[1][0], self.cKeysPosition[1][1])
        # Change it to use actual angles.
        X = []
        Y = []
        coordinates = []
        radius = 30
        for i in range(0, 8):
            if (i == 4):
                radius = 30
            if (i < 4):
                X.append(cX - math.cos(angle) * radius)
                Y.append(cY - math.sin(angle) * radius)
                radius += 60
            else:
                X.append(cX + math.cos(angle) * radius)
                Y.append(cY + math.sin(angle) * radius)
                radius += 60

        print (self.cKeysPosition[1][0], self.cKeysPosition[1][1])
        print ("X: {}".format(X))
        print ("Y: {}".format(Y))
        print ("refKey: {}".format(self.refKey[2]))
        
        origin = 0
        for i in range(0, 8):  
            # Compute distance from key and the center of the setup
            cv2.circle(orig, (int(X[i]), int(Y[i])), 5, self.colors[2], -1)
            dY = self.computeDistance((cX, cY), (setupCenter[0], setupCenter[1]))
            
            if i == 0:
                if (cX > setupCenter[0]):
                    dX = self.computeDistance((setupCenter[0], Y[i]), (X[i], Y[i]))
                    dX = dX + self.computeDistance((cX, cY), (X[i], Y[i]))
                else:
                    dX = self.computeDistance((setupCenter[0], Y[i]), (X[i], Y[i]))
                    dX = -dX + self.computeDistance((cX, cY), (X[i], Y[i]))
                origin = (dX, dY)
                print(origin)
                coordinates.append(origin)

            if i > 0 and i < 4:
                dX = self.computeDistance((X[0], Y[0]), (X[i], Y[i])) + origin[0]
                coordinates.append((dX, dY))
            elif i >= 4 and i < 8:
                dX = self.computeDistance((X[0], Y[0]), (X[i], Y[i])) - origin[0]
                coordinates.append((-dX, dY))

        # Coordinates are returned as [G, A, B, C_high, F, E, D, C_low]
        print("Coordinates: {}".format(coordinates))
        return coordinates


    def computeDistance(self, p1, p2):
        D = Utilities.calculateDistance((p1[0], p1[1]), (p2[0], p2[1])) / self.refKey[2]
        return D
          

    def computeCoordinates(self, setupCenterX, setupCenterY):
        cX, cY = Utilities.midpoint(self.cKeysPosition[1][0], self.cKeysPosition[1][1])
        originX = setupCenterX - cX
        originY = cY - setupCenterY
        print (originX, originY)
        (tl, tr, br, bl) = self.cKeysPosition[0][0]
        (tlblX, tlblY) = Utilities.midpoint(tl, bl)
        (trbrX, trbrY) = Utilities.midpoint(tr, br)
        
        # compute the Euclidean distance between the midpoints,
        # then construct the reference object
        D = Utilities.calculateDistance((tlblX, tlblY), (trbrX, trbrY))
        self.refKey = (self.cKeysPosition[0][0], (cX, cY), D / 21.5)
#        keyWidth = (Utilities.calculateDistance(tr, tl) / 21.5)
#        print ("keyWidth", keyWidth)
        tmpX = setupCenterX - self.cKeysPosition[1][0][0]
        tmpY = self.cKeysPosition[1][0][1] - setupCenterY
        tmpX -= originX
        tmpX = -tmpX
        tmpY -= originY
        print (tmpX, tmpY)
        x = tmpX / self.refKey[2]
        y = tmpY / self.refKey[2]
        print (x, y)


    def getDistances(self, boxes, orig, angle, setupCenter):
        for box in boxes:
            # compute the center of the bounding box
            cX = np.average(box[:, 0])
            cY = np.average(box[:, 1])
            if self.refKey is None:
                (tl, tr, br, bl) = box
                (tlblX, tlblY) = Utilities.midpoint(tl, bl)
                (trbrX, trbrY) = Utilities.midpoint(tr, br)
        
                # compute the Euclidean distance between the midpoints,
                # then construct the reference object
                D = Utilities.calculateDistance((tlblX, tlblY), (trbrX, trbrY))
                self.refKey = (box, (cX, cY), D / 21.5)
                break

            (xA, yA) = self.refKey[1]
        
            cv2.circle(orig, (int(xA), int(yA)), 5, self.colors[3], -1)
            cv2.circle(orig, (int(cX), int(cY)), 5, self.colors[3], -1)
            cv2.line(orig, (int(xA), int(yA)), (int(cX), int(cY)),
                    self.colors[3], 2)

            # compute the Euclidean distance between the coordinates,
            # and then convert the distance in pixels to distance in
            # units
            D = Utilities.calculateDistance((xA, yA), (cX, cY)) / self.refKey[2]
            (mX, mY) = Utilities.midpoint((xA, yA), (cX, cY))
            cv2.putText(orig, "{:.1f}mm".format(D), (int(mX), int(mY - 10)),
            cv2.FONT_HERSHEY_SIMPLEX, 0.55, self.colors[3], 2)                    
        return self.getCoordinates(orig, angle, setupCenter)

    def getBorders(self, frame):
        boxes, centroids = self.bordersTracker.getBordersTracking(frame)
        centroids.sort()
        self.borderPosition = (boxes, centroids)
        
        return self.borderPosition


    def drawCKeys(self, frame, boxes, centroids):
        for i, box in enumerate(boxes):
            cv2.drawContours(frame, [box], 0, (0, 255, 0), 2)
            cv2.circle(frame, (centroids[i][0], centroids[i][1]), 7, (0, 0, 255), -1)
        
        self.getXylophoneCenter(frame, centroids)
        return frame

    def drawPlane(self, frame, boxes):
        planePoints = np.array((boxes[0][0],boxes[1][1], boxes[1][2], boxes[0][3]))
        cv2.polylines(frame,[planePoints],True,(255,0,0), 5)
        return frame
    

    def getXylophoneCenter(self, frame, centroids):
        cx, cy = Utilities.midpoint(centroids[0], centroids[1])
        cv2.circle(frame, (int(cx), int(cy)), 7, (255, 0, 0), -1)
        return frame

    def getStickPosition(self, frame):
        x, y, r = self.circleTracker.trackCircle(frame)
        if (x==0 or y==0 or r == 0):
            return self.stickPosition
        if self.stickPosition is not None:
            if (self.stickPosition[0] != x and self.stickPosition[1] != y):
                self.stickPosition = (x, y, r)
        else:
            self.stickPosition = (x, y, r)

        return self.stickPosition

    def drawStick(self, frame, x, y, r):
        cv2.circle(frame, (x, y), r, (0, 255, 0), 2)
        cv2.circle(frame, (x, y), 2, (0,0,255), 3)
        return frame

