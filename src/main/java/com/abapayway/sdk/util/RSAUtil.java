package com.abapayway.sdk.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RSAUtil {
    // Load private key
    public static PrivateKey loadPrivateKeyFromPEM(String pemFilePath) throws Exception {
        try (InputStream inputStream = RSAUtil.class.getResourceAsStream(pemFilePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Private key file not found at: " + pemFilePath);
            }

            String key = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            
            key = key.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", ""); // Remove whitespaces

            byte[] keyBytes = Base64.getDecoder().decode(key);

            // Clear the PEM string to minimize sensitive data exposure in memory
            Arrays.fill(key.toCharArray(), '\0'); // Clear string buffer in memory

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            // Clear the byte array after use
            Arrays.fill(keyBytes, (byte) 0); // Clear sensitive data

            return keyFactory.generatePrivate(spec);
        }
    }

    // Load public key
    public static PublicKey loadPublicKeyFromPEM(String pemFilePath) throws Exception {
        try (InputStream inputStream = RSAUtil.class.getResourceAsStream(pemFilePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Public key file not found at: " + pemFilePath);
            }
    
            String key = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            
            key = key.replace("-----BEGIN PUBLIC KEY-----", "")
                     .replace("-----END PUBLIC KEY-----", "")
                     .replaceAll("\\s", ""); // Remove whitespaces
    
            byte[] keyBytes = Base64.getDecoder().decode(key);
    
            // Clear the PEM string to minimize sensitive data exposure in memory
            Arrays.fill(key.toCharArray(), '\0'); // Clear string buffer in memory
    
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    
            // Clear the byte array after use
            Arrays.fill(keyBytes, (byte) 0); // Clear sensitive data
    
            return keyFactory.generatePublic(spec);
        }
    }
}

