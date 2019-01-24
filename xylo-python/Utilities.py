import math
from numpy import ones,vstack
from numpy.linalg import lstsq

def midpoint(ptA, ptB):
	return ((ptA[0] + ptB[0]) * 0.5, (ptA[1] + ptB[1]) * 0.5)

def calculateDistance(a,b):
    dist = math.sqrt((b[0] - a[0])**2 + (b[1] - b[1])**2)    
    return dist

def calculateLineThroughPoints(a, b):
    points = [a,b]
    x_coords, y_coords = zip(*points)
    A = vstack([x_coords,ones(len(x_coords))]).T
    m, c = lstsq(A, y_coords)[0]
    print("Line Solution is y = {m}x + {c}".format(m=m,c=c))
    return m, c

def calculateAngle(m1, m2):
    return math.pi - math.atan((m1-m2)/(1+m1*m2))