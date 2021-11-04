package com.islet.domain.vo.mail;

import lombok.*;

/**
 * @author tangJM.
 * @date 2021/11/4
 * @description
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListVO {

    /**
     * id
     */
    private Long id;

    /**
     * 分类
     */
    private String category;
}
