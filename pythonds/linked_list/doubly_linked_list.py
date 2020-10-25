# singly linked list

class node:
    def __init__(self, element):
        self.prev = None
        self.data = element
        self.next = None
    def isLast(self):
        return self.next == None
class Dlinked_list:
    def __init__(self, head = None):
        self.head = head
        self.tail = head
    def clear(self):
        self.head = self.tail = None
    def insertAt(self, element, index):
        if index == 0:
            self.insert(element)
            return True
        if self.isEmpty() and index >= 1:
            return False
        i = 1
        j = self.head
        while j.next != None:
            if i == index:
                a = node(element)
                a.next = j.next
                j.next.prev = a
                j.next = a
                a.prev = j

                return True
            i += 1
            j = j.next
        if i == index and j.isLast():
            a = node(element)
            j.next = self.tail = a
            a.prev = j
        return False
    def link(self, a, b):
        a.next = b
        b.prev = a


    def insert(self, element):
        if self.isEmpty():
            self.tail = self.head = node(element)
        else :
            a = self.head
            n = node(element)
            n.next = a
            a.prev = self.head = n
            print(a.data, "<->", n.data)
        return True

    def insertEnd(self, element):
        if self.isEmpty():
            self.tail = self.head = node(element)
        else:
            a = self.tail
            n = node(element)
            n.prev = a
            a.next = self.tail = n
            print(a.data,"<->",n.data)
        return True


    def isEmpty(self):
        return self.head == None

    def deleteAt(self, index):
        if self.head == None:
            return None
        if index == 0:
            a = self.head.data
            if self.head == self.tail:
                self.clear()
            else:
                b = self.head
                self.head = b.next
                b.next.prev = None
            return a

        i = 1
        j = self.head
        while j.next != None:
            if i == index:
                a = j.next
                j.next = a.next
                if j.isLast():
                    self.tail = j
                else:
                    a.next.prev = j
                return a.data
            j = j.next
            i += 1
        return None

    def isonly(self):
        return self.head != None and self.head == self.tail

    def delEnd(self):
        if self.isEmpty():
            return
        if self.isonly():
            a = self.head.data
            self.tail = self.head = None
            return a
        a = self.tail.data
        self.tail = self.tail.prev
        self.tail.next = None
        return a


    def delFirst(self):
        if self.isEmpty():
            return
        if self.isonly():
            a = self.head.data
            self.tail = self.head = None
            return a
        a = self.head.data
        self.head = self.head.next
        self.head.prev = None
        return a

    def delE(self, element):
        if self.isEmpty() or (self.isonly() and self.head.data != element):
            return None
        if self.head.data == element:
            if self.isonly(): self.clear()
            self.head = self.head.next
            self.head.prev = None
            return True

        i = self.head

        while i.next != None:
            if element == i.next.data:
                i.next = i.next.next

                if i.next.isLast():
                    self.tail = i.next
                else:
                    i.next.prev = i
                return True
            i = i.next
        return False

    def getAt(self, index):
        i = 0
        j = self.head
        while j != None:
            if i == index:
                return j.data
            i += 1
            j = j.next
    def getHead(self):
        return None if self.isEmpty() else self.head.data
    def getTail(self):
        return None if self.isEmpty() else self.tail.data
    def __str__(self, reverse = False):
        if self.isEmpty():
            return "head ->None<- tail"
        if reverse:
            j = self.tail
            st = "tail -> "
            while j != None:
                st += str(j.data) + " <-> "
                j = j.prev
            st += "\b\b head - tail = " +  str(self.tail.data)
            return st
        j = self.head
        st = "head -> "
        while j != None:
            st += str(j.data) + " <-> "
            j = j.next
        st += "\b\b tail = " + str(self.tail.data)
        return st



l = Dlinked_list()
print(
    l.insertEnd(5),
    l.insertEnd(6),
    l.insertEnd(10),
    l.insertEnd(12)
)

print(str(l))
print(l.__str__(True))
print(l.insert(20))
print(str(l))
print(l.__str__(True))
print(l.insertEnd(100))
print(str(l))
print(l.__str__(True))
print(l.insertAt(100, 3))
print(str(l))
print(l.__str__(True))

print(l.insertAt(100, 5))
print(str(l))
print(l .insertAt(100, 7))
print(str(l))
print(l.insertEnd(100))
print(str(l))
print(l.__str__(True))
print(l.deleteAt(2))
print(str(l))
print(l.__str__(True))
print(l.insertAt(100, 10))
print(str(l))
print(l.__str__(True))
print(l.insertAt(99, 9))
print(str(l))
print(l.__str__(True))
print(l.deleteAt(8))
print(str(l))
print(l.__str__(True))
print(l.deleteAt(8))
print(str(l))
print(l.__str__(True))
print(l.delE(100))
print(str(l))
print(l.__str__(True))
print(l.insertEnd(50))
print(str(l))
print(l.__str__(True))
print(l.delEnd())
print(str(l))
print(l.__str__(True))
print(l.delEnd())
print(str(l))
print(l.__str__(True))
print(l.delEnd())
print(str(l))
print(l.__str__(True))
print(l.delFirst())
print(str(l))
print(l.__str__(True))
print(l.deleteAt(2))
print(str(l))
print(l.__str__(True))
print(l.deleteAt(2))
print(str(l))
print(l.__str__(True))
print(l.deleteAt(0))
print(str(l))
print(l.__str__(True))
print(l.deleteAt(0))
print(str(l))
print(l.__str__(True))
print(l.deleteAt(56))
print(l.delFirst())
print(l.delE(5))
print(l.delEnd())
print(l.getAt(5))
print(
    l.insertEnd(5),
    l.insertEnd(6),
    l.insertEnd(10),
    l.insertEnd(12)
)
print(str(l))
print(l.getHead())
print(l.getTail())



