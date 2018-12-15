package org.marceloleite.tutorials.dsl.compilationcustomizers

import org.codehaus.groovy.control.customizers.ImportCustomizer

def icz = new ImportCustomizer()
icz.addImports('java.util.concurrent.atomic.AtomicInteger', 'java.util.concurrent.ConcurrentHashMap')
icz.addImport('CHM', 'java.util.concurrent.ConcurrentHashMap')
icz.addStaticImport('java.lang.Math', 'PI')
icz.addStaticImport('pi', 'java.lang.Math', 'PI')
icz.addStarImports 'java.util.concurrent'
icz.addStaticStars 'java.lang.Math'