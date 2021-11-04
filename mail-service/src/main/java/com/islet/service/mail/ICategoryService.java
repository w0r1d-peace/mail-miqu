package com.islet.service.mail;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.domain.dto.mail.CategorySaveOrUpdateDTO;
import com.islet.domain.vo.mail.CategoryListVO;
import com.islet.model.mail.Category;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-11
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 查询分类名称
     * @param userId
     * @return
     */
    List<CategoryListVO> list(Long userId);

    /**
     * 新增分类
     * @param dto
     * @return
     */
    Long saveCategory(CategorySaveOrUpdateDTO dto);

    /**
     * 更新分类
     * @param dto
     * @return
     */
    Boolean updateCategory(CategorySaveOrUpdateDTO dto);

    /**
     * 删除分类
     * @param id
     * @return
     */
    Boolean deleteCategory(Long id, Long userId, String createName);
}
