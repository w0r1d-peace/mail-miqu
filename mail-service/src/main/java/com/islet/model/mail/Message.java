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
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 消息类型 1-邮件拉取 2-舆情监控 3-邮件定位 4-邮件轰炸
     */
    private Boolean type;

    /**
     * 是否已读 0-否 1-是
     */
    private Boolean hasRead;

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
