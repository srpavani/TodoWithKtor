package com.diogopavani

//import io.ktor.http.ContentType.Application.Json
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json{
            prettyPrint = true
            isLenient = true
        })
    }
}
