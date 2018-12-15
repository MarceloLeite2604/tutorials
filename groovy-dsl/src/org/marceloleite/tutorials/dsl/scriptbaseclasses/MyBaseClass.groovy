package org.marceloleite.tutorials.dsl.scriptbaseclasses

abstract class MyBaseClass extends Script {
    String name

    public void greet() {
        println "Hello, $name."
    }
}