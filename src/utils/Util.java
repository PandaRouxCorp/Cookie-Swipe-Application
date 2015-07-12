/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * @author Yehouda
 */
public class Util {
    
    public static boolean isWellFormedMail(String mail) {
        return mail.contains("@") && mail.contains(".");
    }
    
}
