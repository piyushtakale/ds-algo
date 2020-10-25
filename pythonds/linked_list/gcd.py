def gcd (a, b):
    if (b == 1):
        return 1
    elif  (b == 0):
        return a
    return gcd(b, a % b)
print(gcd(12, 24))