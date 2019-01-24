from CircleDetection import CircleDetection

class CircleTracker:
    def __init__(self):
        self.circleDetection = CircleDetection()
    # Track Stick "Ball"
    def trackCircle(self, frame):
        circles = self.circleDetection.getCircles(frame)
        if (circles is not None): # Check for circle to be present otherwise return [0,0,0]
            x, y, r = circles[0][0]
            return [x, y, r ]
        else:
            return [0,0,0];
