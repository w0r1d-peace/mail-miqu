package com.islet.model.mail;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("mail_category_keyword")
public class CategoryKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关键字ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 创建人Id
     */
    private Long createId;

    /**
     * 创建者名字
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者id
     */
    private Long updateId;

    /**
     * 更新者名字
     */
    private String updateName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0未删 1已删
     */
    @TableLogic
    private Boolean removed;

}
