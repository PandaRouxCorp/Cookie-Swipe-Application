/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author 626
 */
public class Encryption {
    SecretKeySpec secret = null;

    public void setSecret(String key) {
        this.secret = new SecretKeySpec(key.getBytes(), "AES");
    }
    
    /**
     * @param plainText
     * @return
     * @throws Exception
     */
    public String encrypt(String plainText) throws Exception {   
        if(secret == null)
            generateSecretKey();
                    
        //encrypt the message
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

        return new Base64().encodeBase64String(encryptedTextBytes);
    }
 
    /**
     *
     * @param encryptedText
     * @return
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public String decrypt(String encryptedText) throws Exception {
        if(secret == null)
            generateSecretKey();
        
        byte[] encryptedTextBytes = new Base64().decode(encryptedText);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secret);    
        byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        
        return new String(decryptedTextBytes,"UTF-8");
    }
 
    public void generateSecretKey() {
        try {
            String specificCryptKey = "zertfghzbenrfzer65z+er56365zr45ze4rz4er534z65rzrz53er4135a456r4az4er34z56er42df13z4er564é6z5r4zer4";
            MessageDigest shahash = MessageDigest.getInstance("SHA-1");
            byte[] key = shahash.digest(specificCryptKey.getBytes("UTF-8"));
            key = Arrays.copyOf(key,  16);
            secret = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
