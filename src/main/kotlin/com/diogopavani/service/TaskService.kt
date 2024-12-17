package com.diogopavani.service

import com.diogopavani.models.Task
import com.diogopavani.repository.TaskRepository
import java.sql.SQLException

object TaskService {
    private val taskRepository = TaskRepository()

    fun getAllTasks(): List<Task> {
        return try {
            taskRepository.findAll()
        } catch (e: SQLException) {
            println("Erro ao obter tarefas: ${e.message}")
            emptyList()
        }
    }


    fun createTask(task: Task): Task? {
        return try {
            taskRepository.create(task)
            task // Retorna a tarefa criada
        } catch (e: SQLException) {
            println("Erro ao criar tarefa: ${e.message}")
            null
        }
    }


    fun updateTask(id: String, updatedTask: Task): Task? {
        return try {
            taskRepository.update(id, updatedTask)
        } catch (e: SQLException) {
            println("Erro ao atualizar tarefa: ${e.message}")
            null
        }
    }


    fun deleteTask(id: String): Boolean {
        return try {
            taskRepository.delete(id)
        } catch (e: SQLException) {
            println("Erro ao deletar tarefa: ${e.message}")
            false
        }
    }
}
