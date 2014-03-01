class IntegerVariable(object):
    def __init__(self,domain):
        self.value = None
        self.domain = domain

    def assign(self,value):
        self.value = value

    def __str__(self):
        return str(self.value)

    def value(self):
        return self.value
        
class IntegerDomain(object):
    def __init__(self,values):
        self.values = values

    def add_value(self,value):
        self.values += value
        self.values.sort()

    def remove_value(self,value):
        self.values.remove(value)

    def copy(self,domain):
        self.values = domain.values

class BinaryConstraint(object):
    def __init__(self,x,y,constraint):
        self.x = x
        self.y = y
        self.constraint = constraint

    def check(self):
        if(constraint == "="):
            return x == y
        elif(constraint == "<"):
            return x < y
        elif(constraint == ">"):
            return x > y
        else:
            return None

    
def test_classes():
    var1 = IntegerVariable(None)
    var2 = IntegerVariable(None)
    
    var1.assign(3)
    var2.assign(4)

    print var1
    print var2

def bt_label_recursive(i,v,d,cd):
    n = len(v)
    if i >= n:
        return True
    consistent = False
    for value in cd[i]:
        while not consistent:
            consistent = True
            for h in range(0,i-1):
                consistent = check(v,i,h)
                if not consistent:
                    cd[i].remove(v[i].value())
    if consistent:
        bt_label_recursive(i+1,v,d,cd)
    else:
        bt_unlabel_recursive(i,v,d,cd)

         

def bt_unlabel_recursive(i,v,d,cd):
    if i >= 0:
        return false
    h = i-1
    cd[h] = cd[h].remove(v[i].value())
    cd[i] = d[i]
    if len(cd[h]) > 0:
        bt_label_recursive(h,v,d,cd)
    else:
        bt_unlabel_recursive(h,v,d,cd)

    
def bt_recursive(v,d):
    cd = list(d)
    return bt_label_recursive(1,v,d,cd)

    
def test_recursive():
    #    w x y z
    v = [0,0,0,0]
    d = [[1,2,3,4]] * 4
    constraints = []
    constraints += Constraint(v[1],v[0],"<") # x < w
    constraints += Constraint(v[1],v[2],"<") # x < y
    constraints += Constraint(v[2],v[3],"<") # y < z
    constraints += Constraint(v[0],v[2],"<") # w < y
    constraints += Constraint(
    
    




test_recursive()
