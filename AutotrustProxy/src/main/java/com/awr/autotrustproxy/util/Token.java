package com.awr.autotrustproxy.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Token {
    public Token() {
        super();
    }
    public String generateToken(String auth, byte[] key) {
        String jwt = Jwts.builder()
                         .setHeaderParam(auth, SignatureAlgorithm.HS256)
                         .signWith(SignatureAlgorithm.HS256, key)
                         .setPayload(auth)
                         .compact();
        return jwt;
    }
}
