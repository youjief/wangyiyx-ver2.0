package com.wyyx.cn.provider.until.dateutil;

import com.wyyx.cn.provider.contants.base.DateContants;
import com.wyyx.cn.provider.contants.base.NumberContants;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/18
 * Time: 11:05
 * Description: No Description
 */
public class TimeUtil {
    public Date getTomorrow(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, day);

        Date date = new Date();
        date = calendar.getTime();
        return date;
    }



    public Date addDays(int days) {

        Date nowDateTime =getTomorrow(1);
        Calendar nowtime = Calendar.getInstance();
        nowtime.setTime(nowDateTime);
        Date now = nowtime.getTime();
        Calendar overtime = Calendar.getInstance();
        overtime.setTime(now);
        overtime.add(Calendar.DAY_OF_YEAR, days);
        Date endtime = overtime.getTime();
        return endtime;

    }

//    public static void main(String[] args) {
//        TimeUtil a=new TimeUtil();
//        System.out.println(a.addDays(11));
//        int i=0;
//    }

}
