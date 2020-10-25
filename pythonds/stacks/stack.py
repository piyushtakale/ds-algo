class Stack():
    capacity = 0
    stack = []
    top = -1
    def __init__(self, capacity):
        self.capacity = capacity
        self.stack = []
        self.top = -1
    def push(self, ele):
        if not self.isFull():
            self.stack.append(ele)
            self.top += 1
            return True
        return False
    def pop(self):
        if not self.isEmpty():
            self.top -= 1
            return self.stack.pop()

        return None
    def peek(self):
        if self.top != -1:
            return self.stack[self.top]
        return None
    def isEmpty(self):
        return self.top == -1
    def isFull(self):
        return self.top + 1 == capacity
    def clear(self):
        self.stack.clear()
        self.top = -1
    def copyTo(self, otherstack):
        if self.top+1 <= otherstack.capacity:
            otherstack.stack = self.stack.copy()
            otherstack.top = self.top
            return True
        return False
    def toArray(self): # toArray
        return self.stack.copy()
    def contains(self, element):
        return self.stack.__contains__(element)
    def print(self):
        print(" ".join(list(map(str, self.stack))))

capacity = int(input())
st = Stack(capacity)
print("push 5",st.push(5))
print("push 10",st.push(10))
print("push 20",st.push(20))
print("push 12",st.push(12))
print("push 20",st.push(20))
print("push 50",st.push(50))
print("push 45",st.push(45))
print("peek",st.peek())
print("poperd",st.pop())
print("push 89",st.push(89))
print("peek",st.peek())
print("toArray",st.toArray())
print("contains", st.contains(50))
print("poped", st.pop())
print("isEmpty",st.isEmpty())
print("printing")
st.print()
print("peek", st.peek())
print("isfull", st.isFull())
print("push 90", st.push(90))
print("isfull", st.isFull())



nested = {'data': ['finding', 23, ['exercises', 'hangout', 34]], 'window': ['part', 'whole', [], 'sum', ['math', 'calculus', 'algebra', 'geometry', 'statistics',['physics', 'chemistry', 'biology']]]}

# Check to see if the string data is a key in nested, if it is, assign True to the variable data, otherwise assign False.
data = nested.keys().__contains__("data")
print(data)
# Check to see if the integer 24 is in the value of the key data, if it is then assign to the variable twentyfour the value of True, otherwise False.

# Check to see that the string 'whole' is not in the value of the key window. If it's not, then assign to the variable whole the value of True, otherwise False.

# Check to see if the string 'physics' is a key in the dictionary nested. If it is, assign to the variable physics, the value of True, otherwise False.

nested_d = {'Beijing':{'China':51, 'USA':36, 'Russia':22, 'Great Britain':19}, 'London':{'USA':46, 'China':38, 'Great Britain':29, 'Russia':22}, 'Rio':{'USA':35, 'Great Britain':22, 'China':20, 'Germany':13}}

US_count = 0
for i in nested_d:
    US_count += nested_d[i]["USA"]

print(US_count)
