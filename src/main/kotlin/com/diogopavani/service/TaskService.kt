package com.diogopavani.service

import com.diogopavani.models.Task

object TaskService {
    private val tasks = mutableListOf<Task>()

    fun getAllTasks(): List<Task> = tasks

    fun createTask(task: Task): Task {
        tasks.add(task)
        return task
    }
    fun updateTask(id: String, updatedTask: Task): Task? {
        val index = tasks.indexOfFirst { it.id == id }
        return if (index != -1) {
            tasks[index] = updatedTask
            updatedTask
        } else {
            null
        }
    }

    fun deleteTask(id: String): Boolean {
        return tasks.removeIf { it.id == id }
    }

}