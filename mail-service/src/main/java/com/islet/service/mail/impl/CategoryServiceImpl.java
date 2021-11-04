package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.domain.dto.mail.CategorySaveOrUpdateDTO;
import com.islet.domain.vo.mail.CategoryKeywordListVO;
import com.islet.domain.vo.mail.CategoryListVO;
import com.islet.exception.BusinessException;
import com.islet.mapper.mail.CategoryMapper;
import com.islet.model.mail.Category;
import com.islet.model.mail.CategoryKeyword;
import com.islet.service.mail.ICategoryKeywordService;
import com.islet.service.mail.ICategoryService;
import com.islet.util.CachedBeanCopierUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-11
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    private ICategoryKeywordService categoryKeywordService;

    @Override
    public List<CategoryListVO> categoryList(Long userId) {
        List<Category> list = super.list(new LambdaQueryWrapper<Category>().eq(Category::getCreateId, userId).eq(Category::getRemoved, false));
        return CachedBeanCopierUtil.copyList(list, CategoryListVO.class);
    }

    @Override
    public Long saveCategory(CategorySaveOrUpdateDTO dto) {
        int count = super.count(new LambdaQueryWrapper<Category>().eq(Category::getCreateId, dto.getUserId()).eq(Category::getCategory, dto.getCategory()));
        if (count > 0) {
            throw new BusinessException("分类已存在");
        }

        Date now = new Date();
        Category category = Category.builder()
                .category(dto.getCategory()).createId(dto.getUserId()).createName(dto.getCreator()).createTime(now)
                .updateId(dto.getUserId()).updateName(dto.getCreator()).updateTime(now)
                .build();
        super.save(category);
        return category.getId();
    }

    @Override
    public Boolean updateCategory(CategorySaveOrUpdateDTO dto) {
        Category category = super.getOne(new LambdaQueryWrapper<Category>().eq(Category::getId, dto.getId()).eq(Category::getRemoved, false));
        if (category == null) {
            throw new RuntimeException();
        }

        int count = super.count(new LambdaQueryWrapper<Category>().ne(Category::getId, dto.getId()).eq(Category::getCreateId, dto.getUserId()).eq(Category::getCategory, dto.getCategory()));
        if (count > 0) {
            throw new BusinessException("分类已存在");
        }

        category.setCategory(dto.getCategory());
        category.setUpdateId(dto.getUserId());
        category.setUpdateName(dto.getCreator());
        category.setUpdateTime(new Date());
        return super.updateById(category);
    }

    @Override
    public Boolean deleteCategory(Long id, Long userId, String createName) {
        return super.update(new LambdaUpdateWrapper<Category>()
                .set(Category::getRemoved, true)
                .set(Category::getUpdateId, userId)
                .set(Category::getUpdateName, createName)
                .set(Category::getUpdateTime, new Date())
                .eq(Category::getId, id)
                .eq(Category::getCreateId, userId)
        );
    }

    @Override
    public List<CategoryKeywordListVO> categoryKeywordList(Long id, Long userId) {
        List<CategoryKeyword> categoryKeywordList = categoryKeywordService.listByCategoryId(id, userId);
        CachedBeanCopierUtil.copyList(categoryKeywordList, CategoryKeywordListVO.class);
        return null;
    }
}
