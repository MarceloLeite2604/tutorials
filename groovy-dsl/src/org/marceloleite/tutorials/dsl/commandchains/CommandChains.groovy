package org.marceloleite.tutorials.dsl.commandchains

show = { println it }
square_root = { Math.sqrt(it) }

def please(action) {
    [the: { what ->
        [of: { n ->
            action(what(n))
        }]
    }]
}

please show the square_root of 100

@Grab('com.google.guava:guava:r09')
import com.google.common.base.*

def result = Splitter.on(',').trimResults(CharMatcher.is('_' as char)).split("_a ,_b_,c__").iterator().toList()
println result

def split(string) {
    [on: { sep ->
        [trimming: { trimChar ->
            Splitter.on(sep).trimResults(CharMatcher.is(trimChar as char)).split(string).iterator().toList()
        }]
    }]
}

def dslResult = split "_a ,_b_,c__" on ',' trimming '_'
println dslResult
