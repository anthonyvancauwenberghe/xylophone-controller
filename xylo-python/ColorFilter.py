import cv2
import numpy as np

class ColorFilter:

    # Basic color filtering using hsv
    def applyColorFilter(self, X, lower_bound, upper_bound):
        hsv = cv2.cvtColor(X, cv2.COLOR_BGR2HSV)
        mask = cv2.inRange(hsv, lower_bound, upper_bound)
        return mask

    # In mathematical morphology, opening is the dilation of the erosion
    # Used to remove noise
    def applyOpening(self, mask):
        kernel = np.ones((5, 5), np.uint8)
        return cv2.morphologyEx(mask, cv2.MORPH_OPEN, kernel)
        
    # Find contour of C Keys
    def getContours(self, opening):
        contours, hierarchy = cv2.findContours(opening, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
        return contours
