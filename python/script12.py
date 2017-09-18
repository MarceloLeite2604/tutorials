# Partial functions

from functools import partial

def multiply(x,y):
        return x * y

# Create a new function that multiplies by 2
double = partial(multiply,2)
print(double(4))

def func(u,v,w,x):
    return u*4 + v*3 + w*2 + x
#Enter your code here to create and print with your partial function
# Objective: Execute "func" as a partial function informing only one parameter and making it result the value 60.
partialfunction=partial(func,4,3,2)
print(partialfunction(31))
