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
public class Bomb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邮件轰炸ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 目标邮箱
     */
    private String target;

    /**
     * 邮件数量
     */
    private Integer emailNumber;

    /**
     * 目标邮箱
     */
    private String account;

    /**
     * 账号数量
     */
    private Integer accountNumber;

    /**
     * 任务状态 1-进行中 2-已完成 3-已终止
     */
    private Boolean status;

    /**
     * 异常原因
     */
    private String exceptionReason;

    /**
     * 发送数量
     */
    private Integer sendNumber;

    /**
     * 备注
     */
    private String description;

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
