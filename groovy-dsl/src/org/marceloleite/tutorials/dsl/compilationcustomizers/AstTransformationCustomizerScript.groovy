package org.marceloleite.tutorials.dsl.compilationcustomizers

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer
import groovy.util.logging.Log

def config = new CompilerConfiguration()

def acz = new ASTTransformationCustomizer(Log, value: 'LOGGER')
config.addCompilationCustomizers(acz)