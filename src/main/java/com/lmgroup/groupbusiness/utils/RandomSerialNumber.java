package com.lmgroup.groupbusiness.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomSerialNumber {
    private static RandomSerialNumber instance;

    public static RandomSerialNumber Instance() {
        if (null == instance) {
            synchronized (RandomSerialNumber.class) {
                if (null == instance) {
                    instance = new RandomSerialNumber();
                }
            }
        }
        return instance;
    }

    private RandomSerialNumber() {
    }

    public String SNumber() {
        String t = RandomNumber();
        int x = (int) (Math.random() * 900) + 100;
        String serial = t + x;
        return serial;
    }

    public String RandomNumber() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;


    }


}
