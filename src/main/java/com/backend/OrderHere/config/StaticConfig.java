package com.backend.OrderHere.config;

import com.backend.OrderHere.util.Encoder;

import java.security.NoSuchAlgorithmException;

public class StaticConfig {
    public final static String[] ignoreUrl = new String[] {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- API
            "/v1/public/**",
    };

    // JWT
//    public final static String JwtSecretKey;

        public final static String JwtSecretKey = "Xh8wJZcPZfa7t2WdKr4zr9TnRfWP8xYhV1a8dYkHfTw=";

    public final static String JwtPrefix = "Bearer ";
}