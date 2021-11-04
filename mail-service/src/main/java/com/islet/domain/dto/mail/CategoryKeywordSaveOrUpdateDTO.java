package com.islet.domain.dto.mail;

import com.islet.domain.dto.BaseDTO;
import lombok.Data;

/**
 * @author tangJM.
 * @date 2021/11/4
 * @description
 */
@Data
public class CategoryKeywordSaveOrUpdateDTO extends BaseDTO {

    /**
     * 分类关键字id
     */
    private Long id;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 关键字
     */
    private String keyword;
}
