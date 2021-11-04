package com.islet.domain.dto.mail;

import com.islet.domain.dto.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author tangJM.
 * @date 2021/11/4
 * @description
 */
@Data
public class CategorySaveOrUpdateDTO extends BaseDTO {

    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类
     */
    @NotBlank(message = "分类不能为空")
    private String category;
}
