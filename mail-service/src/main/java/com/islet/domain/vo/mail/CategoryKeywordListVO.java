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
public class CategoryKeywordListVO {

    /**
     * id
     */
    private Long id;

    /**
     * 关键字
     */
    private String keyword;
}
