# Sets (lists with no duplicate items).
print(set("my name is Eric and Eric is my name".split()))

a = set(["Jake", "John", "Eric"])
print(a)
b = set(["John", "Jill"])
print(b)

print("Intersection");
print(a.intersection(b))
print(b.intersection(a))

print("Difference")
print(a.difference(b))
print(b.difference(a))

print("Symettric difference")
print(a.symmetric_difference(b))
print(b.symmetric_difference(a))

print("Union")
print(a.union(b))
