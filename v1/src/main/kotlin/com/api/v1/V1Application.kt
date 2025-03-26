package com.api.v1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.modulith.core.ApplicationModules

@SpringBootApplication
class V1Application

fun main(args: Array<String>) {
	val modules = ApplicationModules.of(V1Application::class.java)
	modules.verify()
	runApplication<V1Application>(*args)
}
