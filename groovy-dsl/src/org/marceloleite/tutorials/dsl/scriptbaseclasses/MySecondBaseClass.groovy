package org.marceloleite.tutorials.dsl.scriptbaseclasses

abstract class MySecondBaseClass extends Script {
    int count
    abstract void scriptBody()
    def run() {
        count++
        scriptBody()
        count
    }
}
