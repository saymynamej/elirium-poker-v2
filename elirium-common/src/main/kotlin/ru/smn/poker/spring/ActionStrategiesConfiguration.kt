package ru.smn.poker.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.smn.poker.actions.strategies.ActionStrategy

@Configuration
class ActionStrategiesConfiguration(private val strategies: List<ActionStrategy>) {
    @Bean
    fun strategies() = strategies.associateBy { it.type }
}