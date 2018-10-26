package com.lmgroup.groupbusiness.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
    public static Timestamp DateFormatStamp(Date date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        Timestamp timestamp = Timestamp.valueOf(time);
        return timestamp;
    }

    public static String DateFormatString(Timestamp timestamp) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(timestamp);
        return time;
    }
}
