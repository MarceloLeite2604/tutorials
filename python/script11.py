# Seralization

# Using JSON
import json
json_string = json.dumps([1, 2, 3, "a", "b", "c"])
print(json_string)

# Using Pickle (a Python proprietary data serializer)
import pickle
pickled_string = pickle.dumps([1, 2, 3, "a", "b", "c"])
print(pickle.loads(pickled_string))

# fix this function, so it adds the given name
# and salary pair to salaries_json, and return it
def add_employee(salaries_json, name, salary):
    # Deserialize the JSON "salaries_json" to dictionary ("key, value" structure).
    salaries = json.loads(salaries_json)
    # Adds a dictionary element using "name" as key and "salary" as value.
    salaries[name] = salary
    return json.dumps(salaries)

# test code
salaries = '{"Alfred" : 300, "Jane" : 400 }'
new_salaries = add_employee(salaries, "Me", 800)
decoded_salaries = json.loads(new_salaries)
print(decoded_salaries["Alfred"])
print(decoded_salaries["Jane"])
print(decoded_salaries["Me"])
