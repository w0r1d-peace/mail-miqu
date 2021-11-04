package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.domain.dto.mail.CategoryKeywordSaveOrUpdateDTO;
import com.islet.exception.BusinessException;
import com.islet.mapper.mail.CategoryKeywordMapper;
import com.islet.model.mail.CategoryKeyword;
import com.islet.service.mail.ICategoryKeywordService;
import org.springframework.stereotype.Service;

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
public class CategoryKeywordServiceImpl extends ServiceImpl<CategoryKeywordMapper, CategoryKeyword> implements ICategoryKeywordService {

    @Override
    public List<CategoryKeyword> listByCategoryId(Long categoryId, Long userId) {
        return super.list(new LambdaQueryWrapper<CategoryKeyword>().eq(CategoryKeyword::getCategoryId, categoryId).eq(CategoryKeyword::getCreateId, userId).eq(CategoryKeyword::getRemoved, false));
    }

    @Override
    public Long saveCategoryKeyword(CategoryKeywordSaveOrUpdateDTO dto) {
        int count = super.count(new LambdaQueryWrapper<CategoryKeyword>().eq(CategoryKeyword::getCreateId, dto.getUserId()).eq(CategoryKeyword::getCategoryId, dto.getCategoryId()).eq(CategoryKeyword::getRemoved, false));
        if (count > 0) {
            throw new BusinessException("关键字已存在");
        }

        Date now = new Date();
        CategoryKeyword categoryKeyword = CategoryKeyword.builder()
                .categoryId(dto.getCategoryId()).keyword(dto.getKeyword())
                .createId(dto.getUserId()).createName(dto.getCreator()).createTime(now)
                .updateId(dto.getUserId()).updateName(dto.getCreator()).updateTime(now)
                .build();
        super.save(categoryKeyword);
        return categoryKeyword.getId();
    }

    @Override
    public Boolean updateCategoryKeyword(CategoryKeywordSaveOrUpdateDTO dto) {
        CategoryKeyword categoryKeyword = super.getOne(new LambdaQueryWrapper<CategoryKeyword>().eq(CategoryKeyword::getId, dto.getId()).eq(CategoryKeyword::getCategoryId, dto.getId()).eq(CategoryKeyword::getRemoved, false));
        if (categoryKeyword == null) {
            throw new RuntimeException();
        }

        int count = super.count(new LambdaQueryWrapper<CategoryKeyword>().ne(CategoryKeyword::getId, dto.getId()).eq(CategoryKeyword::getCategoryId, dto.getCategoryId()).eq(CategoryKeyword::getCreateId, dto.getUserId()).eq(CategoryKeyword::getKeyword, dto.getKeyword()).eq(CategoryKeyword::getRemoved, false));
        if (count > 0) {
            throw new BusinessException("关键字已存在");
        }

        categoryKeyword.setKeyword(dto.getKeyword());
        categoryKeyword.setUpdateId(dto.getUserId());
        categoryKeyword.setUpdateName(dto.getCreator());
        categoryKeyword.setUpdateTime(new Date());
        return super.updateById(categoryKeyword);
    }

    @Override
    public Boolean deleteCategoryKeyword(Long id, Long userId, String createName) {
        return super.update(new LambdaUpdateWrapper<CategoryKeyword>()
                .set(CategoryKeyword::getRemoved, true)
                .set(CategoryKeyword::getUpdateId, userId)
                .set(CategoryKeyword::getUpdateName, createName)
                .set(CategoryKeyword::getUpdateTime, new Date())
                .eq(CategoryKeyword::getId, id)
                .eq(CategoryKeyword::getCreateId, userId)
        );
    }
}
