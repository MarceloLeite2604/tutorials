# Imports the user created module "bar" from "foo" package.
import foo.bar

foo.bar.myfoobarfunction()

# An alternative to import the same module.
from foo import bar

bar.myfoobarfunction()
