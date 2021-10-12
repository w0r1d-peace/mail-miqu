package com.islet.model.mail;

import com.baomidou.mybatisplus.annotation.*;
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
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * UUID
     */
    private String uuid;

    /**
     * 发送邮箱账号ID
     */
    private Long accountId;

    /**
     * 发送邮箱账号
     */
    private String source;

    /**
     * 目标邮箱账号
     */
    private String target;

    /**
     * 定位状态 0-未定位 1-已定位
     */
    private Integer status;

    /**
     * 安全链路
     */
    private Long vpnId;

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
    private Long removed;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * IP记录
     */
    @TableField("ipJson")
    private String ipJson;


}
