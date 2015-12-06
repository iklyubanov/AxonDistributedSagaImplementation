package ru.iklyubanov.diploma.saga.gcore.annotation

import groovy.transform.AnnotationCollector
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

/**
 * аннотация для объявления команд
 * Created by ivan on 12/3/2015.
 */
@TupleConstructor
@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
@AnnotationCollector()
@interface Command {

}