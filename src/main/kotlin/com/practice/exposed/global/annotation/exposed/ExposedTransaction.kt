package com.practice.exposed.global.annotation.exposed

import org.jetbrains.exposed.sql.Table
import org.springframework.core.annotation.AliasFor
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Transactional
annotation class ExposedTransaction(
    val target: Array<KClass<out Table>>,
    @get: AliasFor(annotation = Transactional::class)
    val noRollbackFor: Array<KClass<out Throwable>> = [],
    @get: AliasFor(annotation = Transactional::class)
    val rollbackFor: Array<KClass<out Throwable>> = [Exception::class]
)
