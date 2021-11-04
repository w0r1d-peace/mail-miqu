package com.islet.service.mail;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.domain.dto.mail.CategoryKeywordSaveOrUpdateDTO;
import com.islet.model.mail.CategoryKeyword;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-11
 */
public interface ICategoryKeywordService extends IService<CategoryKeyword> {

    /**
     * 获取分类关键字列表
     * @param userId
     * @return
     */
    List<CategoryKeyword> listByCategoryId(Long categoryId, Long userId);

    /**
     * 新增分类关键字
     * @param dto
     * @return
     */
    Long saveCategoryKeyword(CategoryKeywordSaveOrUpdateDTO dto);

    /**
     * 更新分类关键字
     * @param dto
     * @return
     */
    Boolean updateCategoryKeyword(CategoryKeywordSaveOrUpdateDTO dto);

    /**
     * 刪除分类关键字
     * @param id
     * @param userId
     * @param createName
     * @return
     */
    Boolean deleteCategoryKeyword(Long id, Long userId, String createName);
}
