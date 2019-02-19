package com.example.kotlinapidemo

data class PassTime(val duration: Int, val risetime: Long)

data class Request(val altitude: Int,
                   val datetime: Long,
                   val latitude: Float,
                   val longitude: Float,
                   val passes: Int)

data class ISSResponse(val message: String,
                       val reason: String,
                       val request: Request,
                       val response: List<PassTime> = emptyList())

