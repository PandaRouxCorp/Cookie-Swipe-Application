/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author 626
 */
public class Encryption {
    private static final String password = "panda";
    private static String salt;
    private static int pswdIterations = 65536  ;
    private static int keySize = 128;
    private byte[] ivBytes;
    
    /**
     * @param plainText
     * @return
     * @throws Exception
     */
    public String encrypt(String plainText) throws Exception {   
//        Passage en hash
//        StringBuffer hash = new StringBuffer();
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA-512");
//                md.update(("PandaRouxCorpProtegeVotreMdp" +password).getBytes("UTF-8"));
//                byte[] digest = md.digest();
//                for (int i = 0; i < digest.length; i++) {
//                    hash.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
//                }
//            }catch (NoSuchAlgorithmException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (UnsupportedEncodingException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return new String(hash);
        //get salt
        salt = generateSalt();      
        byte[] saltBytes = salt.getBytes("UTF-8");
         
        // Derive the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(), 
                saltBytes, 
                pswdIterations, 
                keySize
                );
 
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
 
        //encrypt the message
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = cipher.getParameters();
        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
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

        byte[] saltBytes = salt.getBytes("UTF-8");
        byte[] encryptedTextBytes = new Base64().decodeBase64(encryptedText);

        // Derive the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                saltBytes,
                pswdIterations,
                keySize
        );

        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

        // Decrypt the message
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));

        byte[] decryptedTextBytes = null;
        try {
            decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return new String(decryptedTextBytes);
    }

    public String generateSalt() {
        return "PandaRouxCorp";
    }
}
