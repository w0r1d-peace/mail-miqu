package com.islet.model.mail;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 邮件任务池，每个用户最多只有20条数据
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("mail_task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 邮箱帐号
     */
    private String email;

    /**
     * 邮箱密码
     */
    private String password;

    /**
     * 连接状态 0-无状态 1-连接中 2-连接异常
     */
    private Integer connStatus;

    /**
     * 连接异常原因
     */
    private String connExceptionReason;

    /**
     * 服务器端口
     */
    private Integer port;

    /**
     * 收件服务器
     */
    private String host;

    /**
     * 是否支持SSL 0-否 1-是
     */
    private Boolean hasSsl;

    /**
     * 邮箱类型 1-imap 2-pop3 4-exchange
     */
    private Integer protocolType;

    /**
     * 是否监控 0-否 1-是
     */
    private Boolean hasMonitoring;

    /**
     * 重点关注目标 0-否 1-是
     */
    private Boolean hasEmphasis;

    /**
     * 描述
     */
    private String description;

    /**
     * 已读邮件数
     */
    private Integer readNumber;

    /**
     * 未读邮件数
     */
    private Integer unReadNumber;

    /**
     * 敏感邮件数
     */
    private Integer sensitiveNumber;

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
