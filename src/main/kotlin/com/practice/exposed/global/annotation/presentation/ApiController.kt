package com.practice.exposed.global.annotation.presentation

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
annotation class ApiController(
    @get: AliasFor(annotation = RequestMapping::class)
    val value: String = ""
)
