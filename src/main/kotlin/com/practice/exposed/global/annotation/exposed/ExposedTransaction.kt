package com.practice.exposed.global.annotation.exposed

import org.jetbrains.exposed.sql.Table
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ExposedTransaction(
    val target: KClass<out Table>,
)
