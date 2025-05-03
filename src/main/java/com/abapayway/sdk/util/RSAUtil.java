package com.abapayway.sdk.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {
    // Load private key
    public static PrivateKey loadPrivateKey(String privateKeyPEM) throws Exception {
        String privateKeyPEMCleaned = privateKeyPEM
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replaceAll("\\s", ""); // Remove all whitespace (spaces, newlines, tabs, etc.)

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEMCleaned);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return keyFactory.generatePrivate(keySpec);
    }

    // Load public key
    public static PublicKey loadPublicKey(String publicKeyPEM) throws Exception {
        String publicKeyPEMCleaned = publicKeyPEM
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replaceAll("\\s", ""); // removes all whitespace, including \n and \r
    
        byte[] encoded = Base64.getDecoder().decode(publicKeyPEMCleaned);
    
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded); // Correct spec for public key
        return keyFactory.generatePublic(keySpec);
    }
}

