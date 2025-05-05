package com.abapayway.sdk.util;

import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
public class MerchantAuthUtil {
  public static String encryptMerchantAuth(String merchantId, String tranId, String refundAmount, PublicKey rsaPublicKey) throws Exception {
      String data = String.format("{\"mc_id\":\"%s\", \"tran_id\":\"%s\", \"refund_amount\":\"%s\"}", merchantId, tranId, refundAmount);
      
      int maxLength = 117;
      
      StringBuilder encryptedOutput = new StringBuilder();
      
      while (!data.isEmpty()) {
          String chunk = data.substring(0, Math.min(data.length(), maxLength));
          data = data.substring(chunk.length());
          
          byte[] encryptedChunk = encryptChunk(chunk, rsaPublicKey);
          
          encryptedOutput.append(Base64.getEncoder().encodeToString(encryptedChunk));
      }
      return encryptedOutput.toString();
  }
  private static byte[] encryptChunk(String data, PublicKey rsaPublicKey) throws Exception {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
      return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
  }
}
