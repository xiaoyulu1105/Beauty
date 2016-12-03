package com.lu.beauty.tools;

import android.util.Log;

import com.appeaser.deckview.utilities.DVConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 * 获取当前时间
 */

public class AcquisitionTime {


    public static ArrayList<String> getDay(){
        ArrayList<String> arrayListDay = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (0 == i) {
                arrayListDay.add("TODAY");
            } else if (1 == i) {
                arrayListDay.add("YESTERDAY");
            }else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日",
                    Locale.getDefault());
            String time2 = sdf.format(getDate() - i * 24 * 60 * 60 * 1000);

            Log.d("AcquisitionTime55", time2);
            String day = time2.substring(8, 10);
            String mouth = time2.substring(5, 7).replace("01", "JAN").replace("02", "FEB").replace("03", "MAR").replace("04","APR")
                .replace("05", "MAY").replace("06","JUN").replace("07", "JUL").replace("08","AUG").replace("09", "SEPT")
                .replace("10", "OCT").replace("11", "NOV").replace("12", "DEC");
            arrayListDay.add(mouth + "." + day);
            }
        }
        return arrayListDay;

//        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String    date =    sDateFormat.format(new java.util.Date());
//        String dateMouth = date.substring(5, 10);
//        Log.d("AcquisitionTime1234", dateMouth);
//
//        Log.d("AcquisitionTime123", dateMouth.substring(3, 5));
//        int day = Integer.parseInt(dateMouth.substring(3,5));
//
//        return day;
    }

    public static Long getDate(){
        long time = new Date().getTime();
        Log.d("AcquisitionTime", "time:" + time);
        return time;
    }

}
