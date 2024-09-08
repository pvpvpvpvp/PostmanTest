package com.example.postmantestapi.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Slf4j
public class EncryptionFilter implements Filter {

    private static final String SECRET_KEY = "1234567890123456"; // 16바이트 길이

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getHeader("dec") != null) {
            RequestWrapper requestWrapper = new RequestWrapper(httpRequest);
            String encryptedRequestBody = requestWrapper.getBody();

            String decryptedRequestBody = decryptRequest(encryptedRequestBody);
            requestWrapper.setBody(decryptedRequestBody);

            chain.doFilter(requestWrapper, response);
        } else if (httpRequest.getHeader("enc") != null) {
            ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);
            chain.doFilter(request, responseWrapper);

            String originalResponse = new String(responseWrapper.getDataStream(), StandardCharsets.UTF_8);
            String encryptedResponse = encryptResponse(originalResponse);

            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("UTF-8");

            try (PrintWriter out = httpResponse.getWriter()) {
                out.write(encryptedResponse);
                out.flush();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public static String decryptRequest(String encryptedData) {
        try {
            return AESCrypto.decrypt(encryptedData, SECRET_KEY);
        } catch (Exception e) {
            log.info("e {}    encryptedData : {}",e, encryptedData);
            throw new RuntimeException("Failed to decrypt request", e);
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
