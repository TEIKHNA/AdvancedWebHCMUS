package com.hcmus.sakila.config;

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

        // Skip logging for /favicon.ico
        if (req.getRequestURI().equals("/favicon.ico")) {
            chain.doFilter(request, response);
            return;
        }

        logRequest(req);

        // Wrap response to capture the body
        ResponseWrapper responseWrapper = new ResponseWrapper(resp);
        chain.doFilter(request, responseWrapper);

        logResponse(responseWrapper);

        // Write captured response back to client
        byte[] responseData = responseWrapper.getCapturedData();
        response.getOutputStream().write(responseData);
        response.getOutputStream().flush();
    }

    private void logRequest(HttpServletRequest request) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        apiLogger.info("Incoming Request: {} {} | Headers: {} | Body: {}",
                request.getMethod(), request.getRequestURI(),
                Collections.list(request.getHeaderNames()), body);
    }

    private void logResponse(ResponseWrapper responseWrapper) throws IOException {
        String responseBody = new String(responseWrapper.getCapturedData(), StandardCharsets.UTF_8);

        List<String> messages = null;
        try {
            ResponseDto<?> responseDto = objectMapper.readValue(responseBody, ResponseDto.class);
            messages = responseDto.getMessages();
        } catch (Exception e) {
            apiLogger.warn("Failed to parse response body as ResponseDto.");
        }

        apiLogger.info("Outgoing Response: Status: {} | Messages: {}",
                responseWrapper.getStatus(), messages);
    }

    private static class ResponseWrapper extends HttpServletResponseWrapper {
        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        private final PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);
        private final ServletOutputStream servletOutputStream = new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener listener) {
            }

            @Override
            public void write(int b) {
                outputStream.write(b);
            }
        };

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() {
            return writer;
        }

        @Override
        public ServletOutputStream getOutputStream() {
            return servletOutputStream;
        }

        public byte[] getCapturedData() {
            writer.flush(); // Ensure PrintWriter data is written to ByteArrayOutputStream
            return outputStream.toByteArray();
        }
    }
}
