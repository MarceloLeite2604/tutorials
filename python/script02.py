# Functions
def my_function():
    print("Hello from my function!")

def my_function_with_args(username, greeting):
    print("Hello, %s, from my function!, I wish you %s" %(username, greeting))

def sum_two_numbers(a, b):
    return a + b

my_function()

my_function_with_args("John Doe", "a great year!")

x = sum_two_numbers(1, 2)
print(x)

# Classes and objects
class MyClass:
    variable = "blah"

    def functions(self):
        print("This is a message inside the class.")

myobjectx = MyClass()
print(myobjectx.variable)
myobjectx.functions()

# Dictionaries (A "key, value" structure)
phonebook = {}
phonebook["John"] = 938477566
phonebook["Jack"] = 938477564
phonebook["Jill"] = 938472781
print(phonebook)

# Dictionary initialization.
phonebook = {
    "John" : 938477566,
    "Jack" : 938477564,
    "Jill" : 938472781
}
print(phonebook)

# Dictionary iteration.
phonebook = {
    "John" : 938477566,
    "Jack" : 938477564,
    "Jill" : 938472781
}

for name, number in phonebook.items():
    print("Phone number of %s is %d" % (name, number))

# Removing values from dictionary.
phonebook = {
    "John" : 938477566,
    "Jack" : 938477564,
    "Jill" : 938472781
}

del phonebook["John"]
print(phonebook)

phonebook.pop("Jill") # An alternative for item removal.
print(phonebook)
