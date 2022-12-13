package ru.smn.poker.log

import com.fasterxml.jackson.databind.ObjectMapper

class EliriumLogger {
    companion object {
        private val objectMapper: ObjectMapper = ObjectMapper()
        fun print(message: String) {
            println(this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(Log(message)))
        }
    }


    data class Log(val message: String)

}