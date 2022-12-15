package ru.smn.poker.config

import org.springframework.http.server.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import java.security.Principal

@Component
class PrincipalHandShaker : DefaultHandshakeHandler() {

    override fun determineUser(
        request: ServerHttpRequest,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>,
    ): Principal? {
        return StompPrincipal("0040073d-2634-4f1d-8bc9-e1aa2ab20740")
    }

    class StompPrincipal(private val name: String) : Principal {
        override fun getName() = this.name
    }
}