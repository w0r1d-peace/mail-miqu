package com.islet.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author
 * @version 1.0
 * @date 2019/02/19 上午10:43
 * @Description: 放入缓存，提高性能
 */
@Slf4j
public class CachedBeanCopierUtil {

    /**
     * 创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能
     */
    private static final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>();

    public static <S, T> BeanCopier registerToCache(Class<S> sourceClass, Class<T> targetClass) {
        String key = genKey(sourceClass, targetClass);
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(sourceClass, targetClass, false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        return copier;
    }

    public static <S, T> T copy(S source, Class<T> targetClazz) {
        return copy(source, targetClazz, null, null);
    }

    public static <S, T> T copy(S source, Class<T> targetClazz, Consumer<S, T> consumer) {
        return copy(source, targetClazz, null, consumer);
    }

    public static <S, T> T copy(S source, Class<T> targetClazz, Converter converter, Consumer<S, T> consumer) {
        if (source == null || targetClazz == null) {
            return null;
        }
        T target = null;
        try {
            target = BeanUtil.createInstance(targetClazz);
        } catch (Exception e) {
            //ignore
            return null;
        }
        return registerAndCopy(source, target, converter, consumer);
    }

    public static <S, T> T copy(S source, T target) {
        return copy(source, target, null);
    }

    public static <S, T> T copy(S source, T target, Converter converter) {
        if (source == null || target == null) {
            return null;
        }
        return registerAndCopy(source, target, converter, null);
    }

    private static <S, T> T registerAndCopy(S source, T target, Converter converter, Consumer<S, T> consumer) {
        try {
            BeanCopier copier = registerToCache(source.getClass(), target.getClass());
            copier.copy(source, target, converter);
            if (consumer != null) {
                consumer.accept(source, target);
            }
            return target;
        } catch (Exception e) {
            log.error("copy from [{}] to [{}] error {}", source, target, ExceptionUtil.getAsString(e));
        }
        return null;
    }

    public static <S, T> List<T> copyList(List<S> sources, Class<T> target) {
        return copyList(sources, target, null, null);
    }

    public static <S, T> List<T> copyList(List<S> sources, Class<T> target, Consumer<S, T> consumer) {
        return copyList(sources, target, consumer, null);
    }

    public static <S, T> List<T> copyList(List<S> sources, Class<T> target, Consumer<S, T> consumer, Converter converter) {
        if (CollectionUtil.isEmpty(sources)) {
            //历史原因，为了兼容业务方代码
            return new ArrayList<>();
        }

        List<T> resultList = new ArrayList<>(sources.size());
        try {
            for (S o : sources) {
                if (o == null) {
                    continue;
                }
                T destObject = target.newInstance();
                copy(o, destObject, converter);
                if (consumer != null) {
                    consumer.accept(o, destObject);
                }
                resultList.add(destObject);
            }
            return resultList;
        } catch (Exception e) {
            log.error("copy from [{}] to [{}] error {}", sources, target, ExceptionUtil.getAsString(e));
        }
        return resultList;
    }

    private static String genKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.getName() + targetClass.getName();
    }
}
