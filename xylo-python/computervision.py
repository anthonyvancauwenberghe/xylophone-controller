import cv2
import Utilities
from TrackingSystem import TrackingSystem
from BorderTracker import BorderTracker

SHOW_IMAGE = True

trackingSystem = TrackingSystem()
cap = cv2.VideoCapture(1)
cap.set(3, int(1280))
cap.set(4, int(720))

for i in range(30):
    _, frame = cap.read()

""" ROI = cv2.selectROI(frame)
print('Selected ROI: ', ROI) """

ROI = (181, 357, 831, 363)

def getSetupCenter():
    _, frame = cap.read()
    img_crop = frame[int(ROI[1]):int(ROI[1] + ROI[3]), int(ROI[0]):int(ROI[0] + ROI[2])]
    borderTracker = BorderTracker()
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

    if SHOW_IMAGE:
        cv2.imshow('Output', img_crop)
        cv2.waitKey(0)
        cv2.destroyAllWindows()

    return (cX, cY, m, c)

def getVisionData(setupCenterX, setupCenterY, setupM, setupC):
    coordinates = None
    # Load image, resize and convert color to HSV
    for i in range(15):
        _, frame = cap.read()

    img_crop = frame[int(ROI[1]):int(ROI[1] + ROI[3]), int(ROI[0]):int(ROI[0] + ROI[2])]
    cv2.circle(img_crop, (int(setupCenterX), int(setupCenterY)), 7, (255, 0, 0), -1)
    # Get C Keys position from the TrackingSystem
    cKeysPosition = trackingSystem.getCKeys(img_crop)
 
    if cKeysPosition:
        m,c = Utilities.calculateLineThroughPoints(cKeysPosition[1][0], cKeysPosition[1][1])
        angle = Utilities.calculateAngle(setupM, m)
        dist = Utilities.calculateDistance(cKeysPosition[1][0], cKeysPosition[1][1])
        img_crop = trackingSystem.drawCKeys(img_crop, cKeysPosition[0], cKeysPosition[1])
        coordinates = trackingSystem.getDistances(cKeysPosition[0], img_crop, angle, (setupCenterX, setupCenterY))   
        #trackingSystem.computeCoordinates(setupCenterX, setupCenterY, angle)
        # print('C Keys position is {}'.format(cKeysPosition[1]))

    # Get stick position from the TrackingSystem
    stickPosition = trackingSystem.getStickPosition(img_crop)
    if stickPosition:
        img_crop = trackingSystem.drawStick(img_crop, stickPosition[0], stickPosition[1], stickPosition[2])
        # print('Stick position is {} {}'.format(stickPosition[0], stickPosition[1]))

    if SHOW_IMAGE:
        cv2.imshow('Output', img_crop)
        cv2.waitKey(0)
        cap.release()
        cv2.destroyAllWindows()

    return coordinates


# (setupCenterX, setupCenterY, setupM, setupC) = getSetupCenter()
# coordinates = getVisionData(setupCenterX, setupCenterY, setupM, setupC)
