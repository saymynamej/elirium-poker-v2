package ru.smn.poker.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfiguration {

    fun objectMapper() = ObjectMapper().apply {
        registerModule(kotlinModule())
    }

}