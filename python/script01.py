# Numbers, integers, floats and operations.
myint = 7
print(myint)
myfloat = 7.0
print(myfloat)
myfloat = float(7)
print(myfloat)

one = 1
two = 2
three = one + two
print(three)

a, b = 3, 4
print(a, b)

number = 1 + 2 * 3 / 4.0
print(number)

remainder = 11 % 3
print(remainder)

squared = 7 ** 2
cubed = 2 ** 3
print(squared)
print(cubed)


# Lists.
mylist = []
mylist.append(1)
mylist.append(2)
mylist.append(3)
print(mylist[0]) # prints 1
print(mylist[1]) # prints 2
print(mylist[2]) # prints 3

for x in mylist:
    print(x)

even_numbers = [ 2, 4, 6, 8]
odd_numbers = [1, 3, 5, 7, 9]
all_numbers = odd_numbers + even_numbers
print(all_numbers)

print([1, 2, 3] * 3)

mylist = [1, 2, 3]
print("A list: %s" % mylist)


# Strings
print("This line will be printed.")

mystring = 'hello'
print(mystring)
mystring = "hello"
print(mystring)
mystring ="Don't worry about apostrophes."
print(mystring)

hello = "hello"
world = "world"
helloworld = hello + " " + world
print(helloworld)

lostofhellos = "hello" * 10
print(lostofhellos)

name = "John"
print("Hello, %s!" % name)
age = 23
print("%s is %d years old." % (name, age))

astring = "Hello world!"
print(astring)
print(len(astring))
print(astring.index("o"))
print(astring.count("l"))
print(astring[3:7])
print(astring.upper())
print(astring.lower())
print(astring.startswith("He"))
print(astring.endswith("ld!"))
words = astring.split(" ")
print(words)

string_numbers = "01234567890123456789"
print(len(string_numbers))
print(string_numbers[0:20])
print(string_numbers[0:20:2])
print(string_numbers[::-1])

# Boolean operators
x = 2
print(x == 2)
print(x == 3)
print(x < 3)

name = "John"
age = 23
if name == "John" and age == 23:
    print("Your name is John, and you are also 23 years old.")

if name == "John" or name == "Rick":
    print("Your name is either John or Rick.")

# The "in" operator
name = "John"
if name in ["John", "Rick"]:
    print("Your name is either John or Rick.")

# Code blocks and indentations.
x = 1
if x == 1:
    print("x is 1.")

x = 2
if x == 2:
    print("x equals two.")
else:
    print("x does not equal to two.")

# The "is" operator
x = [1, 2, 3]
y = [1, 2, 3]
print(x == y) # True: the content of "x" is equal of the content of "y".
print(x is y) # False: The variable "x" is not "y" (from the instance perspective).

# The "not" operator
print(not False) # Prints "True".
print((not False) == (False)) # Prints "False".

# The "for" loop
primes = [2, 3, 5, 7]
for prime in primes:
    print(prime)

for x in range(5):
    print(x) # Prints 0, 1, 2, 3, 4 and 5.

for x in range(3, 6):
    print(x) # Prints 3, 4, 5 (won't print 6).

# The "while" loop
count = 0
while count < 5:
    print(count)
    count += 1

count = 0
while True:
    print(count)
    count += 1
    if count >= 5:
        break # Ends the loop.

for x in range(10):
    if x % 2 == 0:
        continue # Ignores the rest of the loop commands, going to it next iteration.
    print(x)

# The "else" clause in loops
count = 0
while( count < 5):
    print(count)
    count += 1
else:
    print("count value reached %d" % (count))
