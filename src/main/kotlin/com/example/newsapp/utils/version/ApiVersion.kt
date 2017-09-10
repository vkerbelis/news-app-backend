package com.example.newsapp.utils.version

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiVersion(vararg val value: Int)