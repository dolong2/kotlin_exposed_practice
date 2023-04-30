package com.practice.exposed.global.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class DataSourceConfig{
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.primary")
    fun primaryHikariConfig(): HikariConfig {
        return HikariConfig()
    }


    @Bean
    @Primary
    @Throws(Exception::class)
    fun primaryDataSource(): DataSource {
        return HikariDataSource(primaryHikariConfig())
    }
}