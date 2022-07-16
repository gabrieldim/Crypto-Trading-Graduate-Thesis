package com.example.cryptotrading.config;

public class JWTAuthConstants {

    public static final long EXPIRATION_DATE = 864_000_000; // 10 days
    public static final String SECRET = "test1234test9883";
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
