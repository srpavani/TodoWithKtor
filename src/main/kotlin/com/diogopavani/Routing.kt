package com.diogopavani

import com.diogopavani.models.Task
import com.diogopavani.service.TaskService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/tasks") {
            call.respond(TaskService.getAllTasks())
        }

        post("/tasks/add"){
            val task = call.receive<Task>()
            call.respond(TaskService.createTask(task))
        }

        put("/tasks/{id}") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val updatedTask = call.receive<Task>()
            val result = TaskService.updateTask(id, updatedTask)
            if (result != null) {
                call.respond(result)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }

        delete("/tasks/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val deleted = TaskService.deleteTask(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Task deleted")
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }
    }
}