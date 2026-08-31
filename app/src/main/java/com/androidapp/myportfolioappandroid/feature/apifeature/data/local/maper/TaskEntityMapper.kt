package com.androidapp.myportfolioappandroid.feature.apifeature.data.local.maper

import com.androidapp.myportfolioappandroid.feature.apifeature.data.local.entity.TaskEntity
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task

fun TaskEntity.toDomain() : Task {
    return Task(
        id = this.id,
        title = this.title,
        description = this.description,
        completeYN = this.completeYN
    )
}

fun Task.toEntity() : TaskEntity {
    return TaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        completeYN = this.completeYN
    )
}