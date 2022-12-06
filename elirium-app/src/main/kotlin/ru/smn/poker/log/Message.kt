package ru.smn.poker.log

import com.fasterxml.jackson.databind.ObjectMapper

data class PokerLogger(
    val message: String,
) {
    private val objectMapper: ObjectMapper = ObjectMapper()

    fun log(message: String) {
        val message = PokerLogger(message)
        println(this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(message))
    }

}