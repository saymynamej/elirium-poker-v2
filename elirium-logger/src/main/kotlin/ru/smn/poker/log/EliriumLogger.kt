package ru.smn.poker.log

import com.fasterxml.jackson.databind.ObjectMapper

val objectMapper: ObjectMapper = ObjectMapper()

fun printC(message: String) {
    println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(Log(message)))
}
data class Log(val message: String)