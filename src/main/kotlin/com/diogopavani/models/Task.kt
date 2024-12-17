package com.diogopavani.models

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String? = null,
    val title: String,
    val description: String? = null,
    val completed: Boolean = false
)