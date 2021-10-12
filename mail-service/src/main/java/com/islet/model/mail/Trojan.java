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
public class Trojan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 木马邮件ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 发件账号ID
     */
    private Long accountId;

    /**
     * 发件人
     */
    private String source;

    /**
     * 收件人
     */
    private String target;

    /**
     * 是否开启仿冒邮箱
     */
    private Integer mailSploit;

    /**
     * 仿冒名
     */
    private String mailSploitName;

    /**
     * 仿冒邮箱
     */
    private String mailSploitEmail;

    /**
     * 标题
     */
    private String title;

    /**
     * 安全链路
     */
    private Long vpnId;

    /**
     * 发送状态 1-发送中 2-发送成功 3-发送失败
     */
    private Boolean status;

    /**
     * 邮箱详情ID
     */
    private String trojanDetailId;

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
