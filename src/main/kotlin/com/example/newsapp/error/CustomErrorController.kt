package com.example.newsapp.error

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.web.ErrorAttributes
import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class CustomErrorController : ErrorController {
    @Value("\${debug:false}") private val isDebugBuild = false
    @Autowired private lateinit var errorAttributes: ErrorAttributes

    @RequestMapping(value = PATH)
    internal fun error(
            request: HttpServletRequest,
            response: HttpServletResponse
    ): CustomError {
        return CustomError(getErrorAttributesMap(request, isDebugBuild), response.status)
    }

    override fun getErrorPath(): String {
        return PATH
    }

    private fun getErrorAttributesMap(
            request: HttpServletRequest,
            includeStackTrace: Boolean
    ): Map<String, Any> {
        val requestAttributes = ServletRequestAttributes(request)
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace)
    }

    companion object {
        private const val PATH = "/error"
    }
}