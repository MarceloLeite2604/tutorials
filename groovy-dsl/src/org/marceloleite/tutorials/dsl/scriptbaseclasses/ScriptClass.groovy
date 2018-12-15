package org.marceloleite.tutorials.dsl.scriptbaseclasses

import org.codehaus.groovy.control.CompilerConfiguration

// Binding variables to a Groovy shell script.
def binding = new Binding()
def shell = new GroovyShell(binding)
binding.setVariable('x', 1)
binding.setVariable('y', 3)
shell.evaluate'z=2*x+y'
assert binding.getVariable('z') == 5

// Creating a customized groovy base script class and using it.
def config = new CompilerConfiguration()
config.scriptBaseClass = MyBaseClass.getCanonicalName()
def myBaseClassShell = new GroovyShell(this.class.classLoader, config)
myBaseClassShell.evaluate"""
    setName 'Judith'
    greet()
"""

// Using the @BaseScript annotation.
import groovy.transform.BaseScript
import org.marceloleite.tutorials.dsl.scriptbaseclasses.MyBaseClass
import org.marceloleite.tutorials.dsl.scriptbaseclasses.MySecondBaseClass

@BaseScript // Defines the variable below as the base script for running the following commands.
MyBaseClass myBaseClass
setName 'Marcelo'
greet()

// Defining content before and after the script execution (see MySecondBaseClass)
config.scriptBaseClass = MySecondBaseClass.getCanonicalName()
def mySecondBaseClassShell = new GroovyShell(this.class.classLoader, config)
def script = mySecondBaseClassShell.parse"""
    println 'Ok'
"""

assert script.run() == 1
assert script.run() == 2