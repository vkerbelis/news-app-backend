package com.example.newsapp.utils.version

import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.servlet.mvc.condition.*
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

import java.lang.reflect.Method

class ApiVersionRequestMappingHandlerMapping(
        private val prefix: String
) : RequestMappingHandlerMapping() {
    override fun getMappingForMethod(method: Method, handlerType: Class<*>): RequestMappingInfo? {
        val info: RequestMappingInfo? = super.getMappingForMethod(method, handlerType)
                ?: return null
        return combineToApiInfo(info, handlerType)
    }

    private fun combineToApiInfo(
            info: RequestMappingInfo?,
            handlerType: Class<*>
    ): RequestMappingInfo? {
        return AnnotationUtils.findAnnotation(handlerType, ApiVersion::class.java)
                ?.let { annotation ->
                    val customCondition: RequestCondition<*>? = getCustomTypeCondition(handlerType)
                    createApiVersionInfo(annotation, customCondition)
                            .combine(info)
                } ?: info
    }

    private fun createApiVersionInfo(
            annotation: ApiVersion,
            customCondition: RequestCondition<*>?
    ): RequestMappingInfo {
        val patterns = createAnnotationPatterns(annotation)
        return RequestMappingInfo(
                createPatternsRequestCondition(patterns),
                RequestMethodsRequestCondition(),
                ParamsRequestCondition(),
                HeadersRequestCondition(),
                ConsumesRequestCondition(),
                ProducesRequestCondition(),
                customCondition)
    }

    private fun createAnnotationPatterns(annotation: ApiVersion): Array<String> {
        return annotation.value
                .map { value -> prefix + value.toString() }
                .toTypedArray()
    }

    private fun createPatternsRequestCondition(
            patterns: Array<String>
    ): PatternsRequestCondition {
        return PatternsRequestCondition(
                patterns,
                urlPathHelper,
                pathMatcher,
                useSuffixPatternMatch(),
                useTrailingSlashMatch(),
                fileExtensions
        )
    }
}