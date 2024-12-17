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

        // Rota para obter todas as tarefas
        get("/tasks") {
            val tasks = TaskService.getAllTasks()
            call.respond(HttpStatusCode.OK, tasks)
        }

        // Rota para adicionar uma nova tarefa
        post("/tasks/add") {
            val task = call.receive<Task>()
            val createdTask = TaskService.createTask(task)
            if (createdTask != null) {
                call.respond(HttpStatusCode.Created, createdTask)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Failed to create task")
            }
        }

        // Rota para atualizar uma tarefa existente
        put("/tasks/{id}") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val updatedTask = call.receive<Task>()
            val result = TaskService.updateTask(id, updatedTask)
            if (result != null) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }

        // Rota para deletar uma tarefa
        delete("/tasks/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val deleted = TaskService.deleteTask(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Task deleted successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }
    }
}
