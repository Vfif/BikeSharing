package com.epam.project.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
    public static String encrypt(String password) {
        String encryptPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1, bytes);
            encryptPassword = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptPassword;
    }
}
