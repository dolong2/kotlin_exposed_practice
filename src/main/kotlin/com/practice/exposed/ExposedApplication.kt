package com.practice.exposed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ExposedApplication

fun main(args: Array<String>) {
	runApplication<ExposedApplication>(*args)
}
