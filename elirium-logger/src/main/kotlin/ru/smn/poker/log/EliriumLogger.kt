package ru.smn.poker.log

import com.fasterxml.jackson.databind.ObjectMapper

data class EliriumLogger(
    val message: String,
) {
    private val objectMapper: ObjectMapper = ObjectMapper()

    fun print() {
        println(this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this))
    }

}