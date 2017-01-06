package com.gy.test;

import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/11/24.
 */
public class UtilTester {

    @Test
    public void testTime(){
        long time =1488262915000L;
        SimpleDateFormat format = new SimpleDateFormat();
        Object obj = format.format(time);
        System.out.println(obj);
    }
}
