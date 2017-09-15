# Generators

import random

# This function is a generator. It "yields" numbers to the method who called it.
def lottery():
    # Returns six numbers between 1 and 40.
    for i in range(6):
        yield random.randint(1, 40)

    # Returns a seventh number between 1 and 15.
    yield random.randint(1, 15)

for random_number in lottery():
    print("And the next number is... %d!" %(random_number))
