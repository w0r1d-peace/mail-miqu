package com.islet.model.base;

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
 * @since 2022-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("base_mail_account")
public class MailAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统账号ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 邮箱账号
     */
    private String account;

    /**
     * 邮箱密码
     */
    private String password;

    /**
     * 邮箱服务器
     */
    private String server;

    /**
     * STMP服务器地址
     */
    private String host;

    /**
     * STMP服务器端口
     */
    private Integer port;

    /**
     * 账号状态 0-不可用 1-可用
     */
    private Boolean status;

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
