package com.example.postmantestapi.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class EncryptionFilter implements Filter {

    private static final String SECRET_KEY = "1234567890123456"; // 16바이트 길이

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getHeader("enc") != null) {
            ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);
            chain.doFilter(request, responseWrapper);

            String originalResponse = new String(responseWrapper.getDataStream(), StandardCharsets.UTF_8);
            String encryptedResponse = encryptResponse(originalResponse);

            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("UTF-8");

            try (OutputStream out = httpResponse.getOutputStream()) {
                out.write(encryptedResponse.getBytes(StandardCharsets.UTF_8));
                out.flush();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private String encryptResponse(String response) {
        try {
            return AESCrypto.encrypt(response, SECRET_KEY);
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt response", e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
