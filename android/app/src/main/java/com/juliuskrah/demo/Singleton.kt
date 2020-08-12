package com.juliuskrah.demo

object Singleton {
    lateinit var properties: ApplicationProperties
    fun properties(): ApplicationProperties {
        return properties
    }
}