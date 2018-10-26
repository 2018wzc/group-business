package com.lmgroup.groupbusiness.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtil {

    public static boolean isBlank(String str) {
        boolean result = false;
        if (str == null || str.trim().length() == 0 || str.equalsIgnoreCase("null")) {
            result = true;
        }
        return result;
    }

    public static boolean isNotBlank(String str) {
        boolean result = true;
        if (str == null || str.trim().length() == 0 || str.equalsIgnoreCase("null")) {
            result = false;
        }
        return result;
    }

    public static String convertStreamToString(InputStream is, String encode) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, encode), 8 * 1024);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            sb.delete(0, sb.length());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                return null;
            }
        }
        return sb.toString();
    }
}
