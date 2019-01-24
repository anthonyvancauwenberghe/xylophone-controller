from ColorFilter import ColorFilter
import cv2
import numpy as np
import Utilities
from imutils import perspective
from imutils import contours

class BorderTracker:
    lower_red = np.array([0,70,100])
    upper_red = np.array([255, 255, 255])

    def __init__(self):
        self.colorFilter = ColorFilter()

    def getBordersTracking(self, frame):
        boxes = []
        centroids = []
        kernel = np.ones((5, 5), np.float32) / 25
        blurred = cv2.filter2D(frame, -1, kernel)
        mask = self.colorFilter.applyColorFilter(blurred, self.lower_red, self.upper_red)
        opening = self.colorFilter.applyOpening(mask)        
        contours = self.colorFilter.getContours(opening)

        for c in contours:
            # compute the center of the contour
            M = cv2.moments(c)
            cX = int(M["m10"] / M["m00"])
            cY = int(M["m01"] / M["m00"])
            # get the bounding rect
            x, y, w, h = cv2.boundingRect(c)
            if (w > 20 and h > 20):
                # get the min area rect
                rect = cv2.minAreaRect(c)
                box = cv2.boxPoints(rect)
                box = perspective.order_points(box)
                # convert all coordinates floating point values to int
                box = np.int0(box)
                boxes.append(box)
                centroids.append((cX, cY))

        return [boxes, centroids]

if __name__ == "__main__":
    borderTracker = BorderTracker()
    cap = cv2.VideoCapture(1)
    cap.set(3, int(1280))
    cap.set(4, int(720))

    """ for i in range(15):
        _, frame = cap.read()

    ROI = cv2.selectROI(frame)
    print('Selected ROI: ', ROI) """

    ROI = (221, 387, 780, 333)
        
    _, frame = cap.read()
    img_crop = frame[int(ROI[1]):int(ROI[1] + ROI[3]), int(ROI[0]):int(ROI[0] + ROI[2])]
    boxes, centroids = borderTracker.getBordersTracking(img_crop)
    for i, box in enumerate(boxes):
        cv2.drawContours(img_crop, [box], 0, (0, 255, 0), 2)
        cv2.circle(img_crop, (centroids[i][0], centroids[i][1]), 7, (0, 0, 255), -1)

    cv2.line(img_crop, (centroids[0][0], centroids[0][1]), (centroids[1][0], centroids[1][1]), (0, 0, 255))

    dist = Utilities.calculateDistance(centroids[0], centroids[1])

    cX, cY = Utilities.midpoint(centroids[0], centroids[1])
    print ('Bord center is {} {}'.format(cX, cY))
    cv2.circle(img_crop, (int(cX), int(cY)), 7, (255, 0, 0), -1)

    m,c = Utilities.calculateLineThroughPoints(centroids[0], centroids[1])

    cv2.imshow('Output', img_crop)

    cv2.waitKey(0)
    cap.release()
    cv2.destroyAllWindows()