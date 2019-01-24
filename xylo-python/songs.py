#C0 D E F G A B C1
# 0 1 2 3 4 5 6 7

# 6 5 2 1 1 6 5 2
# B A E D D B A E D G
# 78 137 58 108 49 59 110 52 111


class Note:
    def __init__(self,p,l):
        self.pitch = p
        self.length = l

class Vector:
    def __init__(self,x,y):
        self.x = x
        self.y = y

STAR_WARS = [Note(0,0.5),Note(4,0.5),
             Note(3,0.125),Note(2,0.125),Note(1,0.125),
             Note(7, 0.5),Note(4,0.5),
             Note(3, 0.125), Note(2, 0.125), Note(1, 0.125),
             Note(7, 0.5), Note(4, 0.5),
             Note(3, 0.125), Note(2, 0.125), Note(3, 0.125),Note(1, 0.125)]

SCALE = [Note(0,0.25),Note(1,0.25),Note(2,0.25),Note(3,0.25),Note(4,0.25),Note(5,0.25),Note(6,0.25),Note(7,0.25)]
SCALE2 = [Note(0,0.25),Note(1,0.25),Note(0,0.25),Note(2,0.25),Note(0,0.25),Note(3,0.25),Note(0,0.25),Note(4,0.25),Note(0,0.25),Note(5,0.25),Note(0,0.25),Note(6,0.25),Note(0,0.25),Note(7,0.25),Note(0,0.25)]
SQ = [Note(0,0.25),Note(1,0.25),Note(2,0.25),Note(3,0.25),Note(4,0.25),Note(5,0.25),Note(6,0.25),Note(7,0.25),Note(0,0.25),Note(1,0.25),Note(0,0.25),Note(2,0.25),Note(0,0.25),Note(3,0.25),Note(0,0.25),Note(4,0.25),Note(0,0.25),Note(5,0.25),Note(0,0.25),Note(6,0.25),Note(0,0.25),Note(7,0.25),Note(0,0.25)]
MAT_TEST = [Note(6,78),Note(5,137),Note(2,58),Note(1,108),Note(1,49),Note(6,59),Note(5,110),Note(2,52)]
MAT_TEST2 = [Note(3,58),Note(3,180),Note(5,61),Note(5,140),Note(1,113),Note(4,97),Note(2,106),Note(0,100)]

ODE_TO_JOY = [Note(2,0.25),Note(2,0.25),Note(3,0.25),Note(4,0.25),Note(4,0.25),Note(3,0.25),Note(2,0.25),Note(1,0.25),Note(0,0.25),Note(0,0.25),Note(1,0.25),Note(2,0.25),Note(2,0.3333333333333333),Note(1,0.125),Note(1,0.5),Note(2,0.25),Note(2,0.25),Note(3,0.25),Note(4,0.25),Note(4,0.25),Note(3,0.25),Note(2,0.25),Note(1,0.25),Note(0,0.25),Note(0,0.25),Note(1,0.25),Note(2,0.25),Note(1,0.3333333333333333),Note(0,0.125),Note(0,0.5),Note(1,0.25),Note(1,0.25),Note(2,0.25),Note(0,0.25),Note(1,0.25),Note(2,0.125),Note(3,0.125),Note(2,0.25),Note(0,0.25),Note(1,0.25),Note(2,0.125),Note(3,0.125),Note(2,0.25),Note(1,0.25),Note(0,0.25),Note(1,0.25),Note(4,0.5),Note(2,0.25),Note(2,0.25),Note(3,0.25),Note(4,0.25)]
SCALE3 = [Note(0,1),Note(7,1),Note(0,1),Note(7,1),Note(0,1),Note(7,1),Note(0,1),Note(7,1),Note(0,1),Note(7,1),Note(0,1),Note(7,1),Note(0,1),Note(7,1),Note(0,1)]

JINGLE = [Note(2,0.125),Note(2,0.125),Note(2,0.25),Note(2,0.125),Note(2,0.125),Note(2,0.25),Note(2,0.125),Note(4,0.125),Note(0,0.125),Note(1,0.125),Note(2,0.5),Note(3,0.125),Note(3,0.125),Note(3,0.25),Note(3,0.0625),Note(3,0.125),Note(2,0.125),Note(2,0.125),Note(2,0.0625),Note(2,0.0625),Note(4,0.125),Note(4,0.125),Note(3,0.125),Note(1,0.125),Note(0,0.25)]

STAR_WARS2 = [Note(1,0.3333333333333333),Note(1,0.3333333333333333),Note(1,0.3333333333333333),Note(4,0.5),Note(1,0.5),Note(2,0.125),Note(0,0.25),Note(4,0.125),Note(2,0.125)]

BROTHER_JACOB = [Note(0,0.25),Note(1,0.25),Note(2,0.25),Note(0,0.25),Note(0,0.25),Note(1,0.25),Note(2,0.25),Note(0,0.25),Note(2,0.25),Note(3,0.25),Note(4,0.5),Note(2,0.25),Note(3,0.25),Note(4,0.5),Note(4,0.125),Note(5,0.125),Note(4,0.125),Note(3,0.125),Note(2,0.25),Note(0,0.25),Note(4,0.125),Note(5,0.125),Note(4,0.125),Note(3,0.125),Note(2,0.25),Note(0,0.25),Note(0,0.25),Note(1,0.25),Note(0,0.5),Note(0,0.25),Note(1,0.25),Note(0,0.5)]

DETECT = [Note(0,66),Note(1,58),Note(2,121)]
