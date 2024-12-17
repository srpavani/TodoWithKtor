package com.diogopavani.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection

object DatabaseConnection {
    private val dataSource: HikariDataSource

    init {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/testando1"
            username = System.getenv("DB_USER") ?: "diogopavani"
            password = System.getenv("DB_PASSWORD") ?: ""
            driverClassName = "org.postgresql.Driver"


            maximumPoolSize = 10
            minimumIdle = 5
            idleTimeout = 30000
            connectionTimeout = 20000
            maxLifetime = 1800000
        }

        dataSource = HikariDataSource(config)
    }

    fun getConnection(): Connection {
        return dataSource.connection
    }

    fun closeDataSource() {
        dataSource.close()
    }
}
