package com.practice.exposed.global.annotation.exposed

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Aspect
@Component
class ExposedTransactionProcessor {
    @Around("@annotation(exposedTransaction)")
    fun applyTransaction(joinPoint: ProceedingJoinPoint, exposedTransaction: ExposedTransaction): Any?{
        var proceed: Any? = null
        val target = exposedTransaction.target
        target.forEach {
            transaction {
                SchemaUtils.create(it.objectInstance!!)
            }
        }
        transaction {
            addLogger(StdOutSqlLogger)
            proceed = joinPoint.proceed()
        }
        return proceed
    }
}
