height = [1.87,  1.87, 1.82, 1.91, 1.90, 1.85]
weight = [81.65, 97.52, 95.25, 92.98, 86.18, 88.45]

# Numpy arrays.
import numpy as np

np_height = np.array(height)
np_weight = np.array(weight)

# Element-wise calculations.
bmi = np_weight / np_height ** 2

# Subsetting
print(bmi)

# For each number on "bmi" array, it will print "true"if it is greater than 23. Otherwise it prints "false".
print(bmi > 25)

# Prints every number on "bmi" array which is greater than 23.
print(bmi[bmi > 25])

# Pandas DataFrames
dict = { "country": ["Brazil", "Russia", "India", "China", "South Africa"], "capital": ["Brasilia", "Moscow", "New Dehli", "Beijing", "Pretoria"], "area": [8.516, 17.10, 3.286, 9.597, 1.221], "population": [200.4, 143.5, 1252, 1357, 52.98] }

import pandas as pd
brics = pd.DataFrame(dict)
print(brics)

# Set the index for brics
brics.index = ["BR", "RU", "IN", "CH", "SA"]
print(brics)

# Import csv file.
cars = pd.read_csv('resources/cars.csv')
print(cars)

# Indexing Pandas DataFrames

# Print out country column as Pandas Series
print(cars['cars_per_cap'])

# Print out cuntry column as Pandas DataFrame
print(cars[['cars_per_cap']])

# Print out DataFrame with country and drives_right columns.
print(cars[['cars_per_cap', 'country']])

# Print out first four observations
print(cars[0:4])

# Print out fifth, sixth and seventh observation.
print(cars[4:6])

# Print out observation for Japan.
print(cars.iloc[2])

# Print out observations for Australia and Egypt
print(cars.loc[['AUS', 'EG']])
