package com.hcmus.sakila.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcmus.sakila.dto.response.ResponseDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
@WebFilter("/*") // Applies to all API requests
public class LoggingFilter implements Filter {

    private static final Logger apiLogger = LoggerFactory.getLogger("API_LOGGER");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        ContentCachingRequestWrapper cachedRequestWrapper = new ContentCachingRequestWrapper(req);
        ContentCachingResponseWrapper cachedResponseWrapper = new ContentCachingResponseWrapper(resp);

        // Skip logging for /favicon.ico
        if (req.getRequestURI().equals("/favicon.ico")) {
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(cachedRequestWrapper, cachedResponseWrapper);

        logRequest(cachedRequestWrapper);

        logResponse(cachedResponseWrapper);

        // Write captured response back to client
        cachedResponseWrapper.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String requestBody = getStringFromStream(request.getContentAsByteArray());

        apiLogger.info("Incoming Request: {} {} | Body: {}",
                request.getMethod(), request.getRequestURI(), requestBody);
    }


    private void logResponse(ContentCachingResponseWrapper response) {
        String responseBody = getStringFromStream(response.getContentAsByteArray());

        List<String> messages = null;
        try {
            ResponseDto<?> responseDto = objectMapper.readValue(responseBody, ResponseDto.class);
            messages = responseDto.getMessages();
        } catch (Exception e) {
            apiLogger.warn("Failed to parse response body as ResponseDto.");
        }

        apiLogger.info("Outgoing Response: Status: {} | Messages: {}",
                response.getStatus(), messages);
    }

    private String getStringFromStream(byte[] content) {
        if (content == null || content.length == 0) {
            return "";
        }
        return new String(content, StandardCharsets.UTF_8);
    }
}
