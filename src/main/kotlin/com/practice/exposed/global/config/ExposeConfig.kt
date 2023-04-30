package com.practice.exposed.global.config

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class ExposeConfig(
    private val dataSource: DataSource
) {
    @Bean(name = ["DB"])
    @Primary
    fun primaryTransactionManager() = SpringTransactionManager(dataSource)
}