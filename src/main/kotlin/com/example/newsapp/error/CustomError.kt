package com.example.newsapp.error

data class CustomError(
        private val errorAttributes: Map<String, Any>,
        val status: Int,
        val error: String = errorAttributes["error"]?.toString() ?: "",
        val message: String = errorAttributes["message"]?.toString() ?: "",
        val timeStamp: String = errorAttributes["timestamp"]?.toString() ?: "",
        val trace: String = errorAttributes["trace"]?.toString() ?: ""
)