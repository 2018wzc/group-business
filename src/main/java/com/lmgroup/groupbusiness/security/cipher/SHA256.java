package com.lmgroup.groupbusiness.security.cipher;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {


    public static byte[] encode(String data, String charsetName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(data.getBytes(charsetName));
        return messageDigest.digest();
    }

    public static byte[] encode(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(data.getBytes());
        return messageDigest.digest();
    }

    public static String encodeAsString(String data, String charsetName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Hex.encode(encode(data, charsetName));
    }

    public static String encodeAsString(String data) throws NoSuchAlgorithmException {
        return Hex.encode(encode(data));
    }
}
