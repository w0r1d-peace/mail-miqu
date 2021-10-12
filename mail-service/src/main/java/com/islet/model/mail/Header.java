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
 * 邮件头
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Header implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "email_id", type = IdType.AUTO)
    private Long emailId;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 邮件唯一id，相对于账户的唯一id
     */
    private String uid;

    /**
     * 发件人
     */
    private String fromer;

    /**
     * 收件人JSON，按name=>value,email=>value的键值对形式存入
     */
    private String receiver;

    /**
     * 密送人JSON，格式和收件人一样
     */
    private String bcc;

    /**
     * 抄送人JSON，格式和收件人一样
     */
    private String cc;

    /**
     * 所属文件夹
     */
    private String folder;

    /**
     * 是否已读 1 表示是，0 表示否
     */
    private Boolean hasRead;

    /**
     * 是否包含附件 1 表示是，0 表示否
     */
    private Boolean hasAttachment;

    /**
     * 发送日期
     */
    private Date sendDate;

    /**
     * 所属邮箱
     */
    private String email;

    /**
     * 邮箱标题
     */
    private String title;

    /**
     * 原始邮件存储路径
     */
    private String emlPath;

    /**
     * 是否为置顶文件 1表示是，0表示否
     */
    private Boolean hasTop;

    /**
     * 入库时间
     */
    private Date createTime;

    /**
     * 修改时间，冗余字段
     */
    @Version
    private Date modified;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 所属用户名，冗余字段，提高查询性能
     */
    private String creator;

    /**
     * 逻辑删除 1-是 0-否
     */
    @TableLogic
    private Integer removed;


}
