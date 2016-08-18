package com.gy.test;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/18.
 */
public class DateTester
{
    @Test
    public void testDate(){

      Calendar cal=Calendar.getInstance();
      cal.getTime();
       cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 0);
        Date startDate=cal.getTime();
        System.out.println(startDate);
        //Date startDate=Calendar.getInstance().set(Calendar);
    }

    @Test
    public void testMapper(){}

}
