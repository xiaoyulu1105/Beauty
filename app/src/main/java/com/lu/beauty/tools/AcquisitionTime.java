package com.lu.beauty.tools;

import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 * 获取当前时间
 */

public class AcquisitionTime {

    public static String getMouth(){
        SimpleDateFormat sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String date = sDateFormat.format(new    java.util.Date());
        String dateMouth = date.substring(5, 10);
        String dateEnglish = dateMouth.replace("-", ".").replace("01", "JAN").replace("02", "FEB").replace("03", "MAR").replace("04","APR")
                .replace("05", "MAY").replace("06","JUN").replace("07", "JUL").replace("08","AUG").replace("09", "SEPT")
                .replace("10", "OCT").replace("11", "NOV").replace("12", "DEC");
        String mouth = dateEnglish.substring(0, 4);
        return mouth;
    }

    public static int getDay(){
        SimpleDateFormat sDateFormat = new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String    date =    sDateFormat.format(new    java.util.Date());
        String dateMouth = date.substring(5, 10);
        String dateEnglish = dateMouth.replace("-", ".").replace("01", "JAN").replace("02", "FEB").replace("03", "MAR").replace("04","APR")
                .replace("05", "MAY").replace("06","JUN").replace("07", "JUL").replace("08","AUG").replace("09", "SEPT")
                .replace("10", "OCT").replace("11", "NOV").replace("12", "DEC");
        int day = Integer.parseInt(dateEnglish.substring(4,6));

        return day;
    }

}
