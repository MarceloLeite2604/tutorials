# Code instrospection.

# Below is the module documentation.
"""This is the thirteenth script I've created based on \"learnpython.org\"."""

# Define the Vehicle class.
class Vehicle:
    # Below is the class documentation
    """This class stores informations about a vehicle."""
    name = ""
    kind = "car"
    color = ""
    value = 100.00
    def description(self):
        # Below is the description of the method.
        """This method prints the description of the vehicle."""
        desc_str = "%s is a %s %s worth $%.2f." % (self.name, self.color, self.kind, self.value)
        return desc_str

# Pretty prints the documentation of a method, class, method, etc. (it blocks the execution, so I commented it).
# help(Vehicle)

# Prints the structure of the class "Vehicle".
print(dir(Vehicle))

# Prints this module documentation.
print(__doc__)

# Prints the documentation of the class "Vehicle".
print(Vehicle.__doc__)

# Prints the documentation of the method "description" inside the class "Vehicle".
print(Vehicle.description.__doc__)

vehicle = Vehicle()

# Checks if object "vehicle" has a "kind" attribute.
print(hasattr(vehicle, "kind"))

# Checks if object "vehicle" has a "brand" attribute.
print(hasattr(vehicle, "brand"))
