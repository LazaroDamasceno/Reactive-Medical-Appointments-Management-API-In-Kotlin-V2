package com.api.v1.common

fun String.toState(): States {
    val foundState = States
        .entries
        .firstOrNull { s -> s == States.valueOf(this)
    }
    if (foundState == null) {
        throw NonExistentStateException()
    }
    return States.valueOf(this)
}