package com.example.newsapp

import com.example.newsapp.utils.version.ApiVersionRequestMappingHandlerMapping
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Configuration
open class BaseConfiguration : DelegatingWebMvcConfiguration() {
    override fun createRequestMappingHandlerMapping(): RequestMappingHandlerMapping {
        return ApiVersionRequestMappingHandlerMapping("api/v")
    }
}