package com.islet.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
/**
 * @author tangJM.
 * @date 2021/10/14
 * @description
 */
public class BaseResultConfig {
    private static final Logger logger = LoggerFactory.getLogger(BaseResultConfig.class);
    private static final String PROPERTIES_FILE = "base-msg.properties";
    private static final Properties configProperties = new Properties();

    public BaseResultConfig() {
    }

    public String getMessage(String key, Object... args) {
        String msg = "";
        String template = configProperties.getProperty(key);
        if (!StringUtils.isEmpty(template)) {
            if (template.contains("%s")) {
                msg = String.format(template, args);
            } else {
                msg = template;
            }
        }

        if (msg.contains("com.mysql.jdbc")) {
            logger.info(msg);
            msg = "数据处理失败，请联系客服";
        } else if (msg.contains("Incorrect string value")) {
            logger.info(msg);
            msg = "存在特殊字符，请重新填写";
        } else if (msg.length() > 80) {
            logger.info(msg);
            msg = "操作失败，请联系客服";
        }

        return msg;
    }

    public static BaseResultConfig getInstance() {
        return BaseResultConfig.Holder.INSTANCE;
    }

    /** @deprecated */
    @Deprecated
    public static String getProperty(Integer key) {
        return configProperties.getProperty(key.toString());
    }

    static {
        try {
            InputStream in = BaseResultConfig.class.getClassLoader().getResourceAsStream("base-msg.properties");
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            configProperties.load(reader);
            reader.close();
        } catch (IOException var2) {
            logger.error("==> 初始化 base-msg.properties 失败", var2);
        }

    }

    private interface Holder {
        BaseResultConfig INSTANCE = new BaseResultConfig();
    }
}

