package com.cloudpioneer.dataGushi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/8/9.
 */
public class FileUtil
{
    public static  Properties getResourcePropertiesByName(String pathName) throws IOException
    {
       InputStream is= FileUtil.class.getResourceAsStream(pathName);
        Properties properties=new Properties();
        properties.load(is);
        return properties;
    }
}
