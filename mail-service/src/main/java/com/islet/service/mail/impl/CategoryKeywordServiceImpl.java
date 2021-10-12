package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.mapper.mail.CategoryKeywordMapper;
import com.islet.model.mail.CategoryKeyword;
import com.islet.service.mail.ICategoryKeywordService;
import org.springframework.stereotype.Service;

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

}
