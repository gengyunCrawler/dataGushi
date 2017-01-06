package com.gy.test;

import com.cloudpioneer.dataGushi.service.HttpService;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017/1/6.
 */
public class WxDetailTester {
    @Test
    public void testGetCustomDetail() throws IOException {
        CloseableHttpClient client = HttpClients.custom().build();
        ResourceBundle bundle = PropertyResourceBundle.getBundle("account");
        HttpResponse httpResponse =HttpService.login(bundle.getString("username"),bundle.getString("password"),client);
        String startTime = "1480953600000";
        String endTime = "1483545600000";
        String response = HttpService.getDetailCustom("MzA5NTEyNDAxMQ==",startTime,endTime,client);
        System.out.println();
    }
}
