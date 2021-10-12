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
 * 邮件服务器查询表，后期做成远程更新，供查询服务器主机和端口。exchange无需设置，有自动寻找的功能。不再使用nslookup去对比查询。
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Host implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 域名
     */
    private String domain;

    /**
     * pop3服务器地址
     */
    private String popHost;

    /**
     * imap服务器地址
     */
    private String imapHost;

    /**
     * exchange服务器地址，exchange有自动识别的方法，此字段作为备用字段
     */
    private String exchangeHost;

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
     * smtp服务器地址
     */
    private String smtpHost;

    /**
     * imap端口号
     */
    private Integer imapPort;

    /**
     * pop端口号
     */
    private Integer popPort;

    /**
     * smtp端口号
     */
    private Integer smtpPort;

    /**
     * 冗余字段
     */
    private Integer exchangePort;

    /**
     * 自定义协议顺序
     */
    private String custom;


}
