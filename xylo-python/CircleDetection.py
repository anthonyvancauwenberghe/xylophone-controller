import cv2
import numpy as np

class CircleDetection:

    def getCircles(self, frame):
        frame = cv2.cvtColor(frame, cv2.COLOR_RGB2GRAY)
        frame = cv2.medianBlur(frame, 9)
        circles = cv2.HoughCircles(frame, cv2.HOUGH_GRADIENT, 1, 20, param1=50, param2=30, minRadius=8, maxRadius=35)
        if (circles is not None):
            circles = np.uint16(np.around(circles))
        return circles

