import math

RADIAN = 0.0174533

FLEX_S2 = [0,1.875,-3.65]
STICK_OFFSET = -0.4
L2 = 1.075
L3 = 1.8

def toRadians(d):
    return d * RADIAN

def toDegrees(d):
    return d/RADIAN

def dist(a,b):
    return math.sqrt(math.pow(b[0]-a[0],2)+math.pow(b[1]-a[1],2))

def dist3(a,b):
    return math.sqrt(math.pow(b[0]-a[0],2)+math.pow(b[1]-a[1],2)+math.pow(b[2]-a[2],2))

def law_of_cosines(a,b,c):
    alpha = math.acos( (math.pow(a,2) + math.pow(b,2) - math.pow(c,2)) / (2*a*b))
    return alpha / RADIAN

def tangent_law(a,b,alpha):
    return math.atan(b * math.sin(toRadians(-alpha))/(a+b*math.cos(toRadians(-alpha))))

def getPointOnCircle(origin, radius, degrees):
    x = math.cos(toRadians(degrees)) * radius + origin[0]
    y = -math.sin(toRadians(degrees)) * radius + origin[1]
    return [x,y]

def getCircleArc(radius, x):
    return (math.acos(x/radius)) / RADIAN

def getModTop(origin, radius, degrees):
    p = [origin[0],origin[2]]
    out = getPointOnCircle(p,radius,-90-degrees)
    return [out[0],origin[1],out[1]]

def getModSide(origin, radius, degrees):
    p = [origin[2], origin[1]]
    out = getPointOnCircle(p, radius, -90+degrees)
    return [origin[0], out[1], out[0]]

def getDegTop(origin, radius, pos):
    return 90-getCircleArc(radius, -(pos[0]-origin[0]))

def getOffsetModTop(effect, d):
    dst = dist([effect[0],effect[2]],[FLEX_S2[0],FLEX_S2[2]])
    arc_off = getDegTop(FLEX_S2,dst,effect)
    p = getModTop([FLEX_S2[0],effect[1],FLEX_S2[2]],dst,-d+arc_off)
    return p

# //Forward kinematics

def getEffectorPos(s):
    flex_s3 = getModSide(FLEX_S2,L2,s[1])
    reach = getModSide([STICK_OFFSET,flex_s3[1],flex_s3[2]],L3,s[1]+s[2])
    return getOffsetModTop(reach,s[0])

# //Inverse kinematics

def getDegs(pos):
    print(pos)
    dst = dist([pos[0],pos[2]],[FLEX_S2[0],FLEX_S2[2]])
    arc_off = getDegTop(FLEX_S2,dst,[STICK_OFFSET,pos[1],pos[2]])
    d1 = -(getDegTop(FLEX_S2,dst,pos)-arc_off)
    effect_norm = getOffsetModTop(pos,-d1)
    c = dist3([effect_norm[0]-STICK_OFFSET,effect_norm[1],effect_norm[2]],FLEX_S2)
    d3 = 180 - law_of_cosines(L3,L2,c)

    d2 = 90 - (math.atan((-(pos[1]-FLEX_S2[1]))/(-pos[2]+FLEX_S2[2])) - tangent_law(L2,L3,d3))/RADIAN
    return [d1,d2,d3]
