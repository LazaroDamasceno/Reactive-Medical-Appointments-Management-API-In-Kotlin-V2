package com.api.v1.common

import com.api.v1.common.States.entries

fun String.toState(): States {
    val parsedState = entries
        .firstOrNull { s -> s == States.valueOf(this) }
    if (parsedState == null) {
        throw NonExistentStateException()
    }
    return parsedState
}