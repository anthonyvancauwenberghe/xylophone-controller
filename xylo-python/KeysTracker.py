from ColorFilter import ColorFilter
import cv2
import numpy as np
from imutils import perspective

class KeysTracker:

    lower_blue = np.array([80, 70, 70])
    upper_blue = np.array([120, 255, 255])

    def __init__(self):
        self.colorFilter = ColorFilter()

    def getCKeysTracking(self, frame):
        boxes = []
        centroids = []
        kernel = np.ones((5, 5), np.float32) / 25
        blurred = cv2.filter2D(frame, -1, kernel)
        mask = self.colorFilter.applyColorFilter(blurred, self.lower_blue, self.upper_blue)
        # cv2.imshow('Mask', mask)
        opening = self.colorFilter.applyOpening(mask)
        _, c_keys = cv2.threshold(opening, 110, 150, cv2.THRESH_BINARY)
        # cv2.imshow('Opening with threshold', c_keys)
        contours = self.colorFilter.getContours(c_keys)

        for c in contours:
            # compute the center of the contour
            M = cv2.moments(c)
            cX = int(M["m10"] / M["m00"])
            cY = int(M["m01"] / M["m00"])
            # get the bounding rect
            x, y, w, h = cv2.boundingRect(c)
            if (w > 40):
                # get the min area rect
                rect = cv2.minAreaRect(c)
                box = cv2.boxPoints(rect)
                box = perspective.order_points(box)
                # convert all coordinates floating point values to int
                box = np.int0(box)
                boxes.append(box)
                centroids.append((cX, cY))

        return [boxes, centroids]


