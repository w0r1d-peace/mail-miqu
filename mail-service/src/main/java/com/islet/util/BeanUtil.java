package com.islet.util;

import com.islet.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;


/**
 * Created with IntelliJ IDEA.
 * Date: 2016-03-14
 * Description: bean工具类
 * @author
 */
@Slf4j
public class BeanUtil {

    public static <M> M createInstance(Class<M> cls){
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            throw new BusinessException(e);
        } catch (IllegalAccessException e) {
            throw new BusinessException(e);
        }
    }
}
