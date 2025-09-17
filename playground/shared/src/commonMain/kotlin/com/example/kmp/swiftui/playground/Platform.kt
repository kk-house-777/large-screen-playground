package com.example.kmp.swiftui.playground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform