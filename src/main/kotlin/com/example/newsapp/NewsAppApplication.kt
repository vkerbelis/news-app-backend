package com.example.newsapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class NewsAppApplication

fun main(args: Array<String>) {
    SpringApplication.run(NewsAppApplication::class.java, *args)
}