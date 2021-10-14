package com.islet.config;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author tangJM.
 * @date 2021/10/14
 * @description
 */
public class ResultConfig {
    private static final String PROPERTIES_FILE = "result-msg.properties";
    private static final Properties configProperties = new Properties();

    public ResultConfig() {
    }

    public String getMessage(String key, Object... args) {
        String template = configProperties.getProperty(key);
        return StringUtils.isEmpty(template) ? "" : String.format(template, args);
    }

    public static ResultConfig getInstance() {
        return ResultConfig.Holder.INSTANCE;
    }

    public static String getProperty(Integer key) {
        return configProperties.getProperty(key.toString());
    }

    static {
        try {
            InputStream in = ResultConfig.class.getClassLoader().getResourceAsStream("result-msg.properties");
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            configProperties.load(reader);
            reader.close();
        } catch (IOException var2) {

        }

    }

    private interface Holder {
        ResultConfig INSTANCE = new ResultConfig();
    }
}

