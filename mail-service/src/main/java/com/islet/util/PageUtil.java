package com.islet.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.islet.domain.vo.PageVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tangJM.
 * @date 2020/9/23
 * @description
 */
@Slf4j
public class PageUtil {

    public static <O, T> List<T> classTransform(List<O> list, Class<T> voClass) {
        List<T> voList = list.stream().map(o -> {
            try {
                T t = voClass.newInstance();
                BeanUtils.copyProperties(o, t);
                return t;
            } catch (InstantiationException e) {
                log.error("系统异常",e);
            } catch (IllegalAccessException e) {
                log.error("系统异常",e);
            }
            return null;
        }).collect(Collectors.toList());
        return voList;
    }

    /**
     * 分页统一返回VO
     *
     * @param total    当前满足条件总行数
     * @param current  当前页
     * @param pageSize 当前分页limit
     * @param voList
     * @param <O>
     * @param <T>
     * @return
     */
    public static <O, T> PageVO getPageVO(long total, long current, long pageSize, List<T> voList) {
        PageVO pageVO = new PageVO();
        pageVO.setTotalCount(total);
        pageVO.setCurrentPage(current);
        pageVO.setPageSize(pageSize);
        pageVO.setList(voList);

        long pages = total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1;
        pageVO.setTotalPage(pages);

        return pageVO;
    }

    public static <O, T> PageVO getPageVO(IPage<O> page, Class<T> voClass) {
        List<T> voList = null;
        if (null == voClass) {
            voList = (List<T>) page.getRecords();
        } else {
            voList = classTransform(page.getRecords(), voClass);
        }
        return getPageVO(page.getTotal(), page.getCurrent(), page.getSize(), voList);
    }

    public static <O> PageVO getPageVO(IPage<O> page) {
        return getPageVO(page, null);
    }


    /**
     * 分页插件查询
     *
     * @param selectFunction
     * @param currentPage
     * @param pageSize
     * @param <O>
     * @return
     */
    public static <O> PageVO<O> getPageVO(Function<Page, List<O>> selectFunction, Integer currentPage, Integer pageSize) {

        return getPageVO(selectFunction, currentPage, pageSize, true);
    }

    /**
     * 分页插件查询
     *
     * @param selectFunction
     * @param currentPage
     * @param pageSize
     * @param <O>
     * @return
     */
    public static <O, T> PageVO<T> getPageVOByIPage(Function<Page, IPage<O>> selectFunction, Integer currentPage, Integer pageSize, Class<T> clazz) {
        Page page = new Page();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        page.setSearchCount(true);

        //查询数据库结果
        IPage result = selectFunction.apply(page);

        if (clazz != null) {
            result.setRecords(CachedBeanCopierUtil.copyList(result.getRecords(), clazz));
        }

        return getPageVO(result);
    }


    /**
     * 分页插件查询
     *
     * @param selectFunction
     * @param currentPage
     * @param pageSize
     * @param searchCount    是否查询总数
     * @param <O>
     * @return
     */
    public static <O> PageVO<O> getPageVO(Function<Page, List<O>> selectFunction, Integer currentPage, Integer pageSize, boolean searchCount) {
        Page page = new Page();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        page.setSearchCount(searchCount);

        //查询数据库结果
        List<O> resultList = selectFunction.apply(page);

        page.setRecords(resultList);

        return getPageVO(page, null);
    }

    /**
     * 分页插件查询
     *
     * @param selectFunction
     * @param currentPage
     * @param pageSize
     * @param <O>
     * @return
     */
    public static <O, T> PageVO<T> getPageVO(Function<Page, IPage<O>> selectFunction, Integer currentPage, Integer pageSize, Class<T> voClass) {
        Page page = new Page();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        //查询数据库结果
        IPage<O> iPage = selectFunction.apply(page);

        return getPageVO(iPage, voClass);
    }
}
