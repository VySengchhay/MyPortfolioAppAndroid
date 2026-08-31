package com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val completeYN: String
)

fun Task.isCompleted(): Boolean {
    return this.completeYN == "Y"
}
