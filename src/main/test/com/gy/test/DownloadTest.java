package com.gy.test;

import com.cloudpioneer.dataGushi.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.LineFormatter;
import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;

/**
 * Created by Administrator on 2016/12/13.
 */
public class DownloadTest {
    @Test
    public void testDownload() throws IOException {
        String url = "http://mp.weixin.qq.com/s?__biz=MjA0MTg1NjMyMA==&mid=2651755208&idx=1&sn=9d80d384218d0e0d23e49af733f963f7";
        HttpGet get = new HttpGet(url);
       String content = HttpUtil.searchByRequest(get);
        Html html = new Html(content);
        content = StringUtils.join(html.xpath("//*/text()").all());

        System.out.println(content);
    }
}
