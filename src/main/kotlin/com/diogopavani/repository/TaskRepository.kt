package com.diogopavani.repository

import com.diogopavani.models.Task
import com.diogopavani.config.DatabaseConnection
import java.sql.Connection
import java.sql.SQLException

class TaskRepository {

    private val connection: Connection = DatabaseConnection.getConnection()


    fun findAll(): List<Task> {
        val tasks = mutableListOf<Task>()
        val statement = connection.prepareStatement("SELECT * FROM tasks")
        val resultSet = statement.executeQuery()

        while (resultSet.next()) {
            tasks.add(
                Task(
                    id = resultSet.getString("id"),
                    title = resultSet.getString("title"),
                    description = resultSet.getString("description"),
                    completed = resultSet.getBoolean("completed")
                )
            )
        }
        return tasks
    }


    fun create(task: Task) {
        val statement = connection.prepareStatement(
            "INSERT INTO tasks (title, description, completed) VALUES (?, ?, ?)"
        )
        statement.setString(1, task.title)
        statement.setString(2, task.description)
        statement.setBoolean(3, task.completed)
        statement.executeUpdate()
    }


    fun update(id: String, updatedTask: Task): Task? {
        val statement = connection.prepareStatement(
            "UPDATE tasks SET title = ?, description = ?, completed = ? WHERE id = ?"
        )
        statement.setString(1, updatedTask.title)
        statement.setString(2, updatedTask.description)
        statement.setBoolean(3, updatedTask.completed)
        statement.setString(4, id)

        val rowsAffected = statement.executeUpdate()
        return if (rowsAffected > 0) updatedTask else null
    }


    fun delete(id: String): Boolean {
        val statement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?")
        statement.setString(1, id)
        val rowsAffected = statement.executeUpdate()
        return rowsAffected > 0
    }
}
