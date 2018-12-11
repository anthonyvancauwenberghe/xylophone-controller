#C0 D E F G A B C1
# 0 1 2 3 4 5 6 7

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

SCALE = [Note(0,1),Note(1,1),Note(2,1),Note(3,1),Note(4,1),Note(5,1),Note(6,1),Note(7,1)]

