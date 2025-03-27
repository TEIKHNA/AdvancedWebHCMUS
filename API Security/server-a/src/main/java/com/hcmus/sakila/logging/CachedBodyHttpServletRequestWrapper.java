//package com.hcmus.sakila.logging;
//import jakarta.servlet.ReadListener;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//
//public class CachedBodyHttpServletRequestWrapper extends HttpServletRequestWrapper {
//    private final byte[] body;
//
//    public CachedBodyHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
//        super(request);
//        // Read and cache the body content
//        InputStream inputStream = request.getInputStream();
//        this.body = inputStream.readAllBytes();
//    }
//
//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body);
//        return new ServletInputStream() {
//            @Override
//            public boolean isFinished() {
//                return false;
//            }
//
//            @Override
//            public boolean isReady() {
//                return false;
//            }
//
//            @Override
//            public void setReadListener(ReadListener readListener) {
//
//            }
//
//            @Override
//            public int read() throws IOException {
//                return byteArrayInputStream.read();
//            }
//        };
//    }
//
//    @Override
//    public BufferedReader getReader() throws IOException {
//        return new BufferedReader(new InputStreamReader(this.getInputStream(), StandardCharsets.UTF_8));
//    }
//
//    public String getBody() {
//        return new String(this.body, StandardCharsets.UTF_8);
//    }
//}
