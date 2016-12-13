package com.cloudpioneer.dataGushi.parse;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class WxArticleParser {



    public static String parseByXpath(String htmlStr,String xpath){
        checkNotNull(htmlStr);
        checkNotNull(xpath);
        Html html = new Html(htmlStr);
        List<String>  extracts = html.xpath(xpath).all();
        String value = StringUtils.join(extracts);
        Html contentHtml = new Html(value);
        List<String> values =  contentHtml.xpath("//*/text()").all();
        return StringUtils.join(values);
    }
}
