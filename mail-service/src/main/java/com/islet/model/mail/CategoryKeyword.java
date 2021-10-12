package com.islet.model.mail;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class CategoryKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关键字ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    @Version
    private Date modified;

    /**
     * 逻辑删除 0-否 1-是
     */
    @TableLogic
    private Boolean removed;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建人
     */
    private String creator;


}
