package com.islet.model.mail;

import com.baomidou.mybatisplus.annotation.*;
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
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("mail_information")
public class Information implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 所属邮件ID
     */
    private Long emailId;

    /**
     * 发件人
     */
    private String fromer;

    /**
     * 目标邮箱
     */
    private String email;

    /**
     * 是否已读 1 表示是，0 表示否
     */
    private Boolean hasRead;

    /**
     * 是否为置顶文件 1表示是，0表示否
     */
    private Boolean hasTop;

    /**
     * 重点关注目标 0-否 1-是
     */
    private Boolean hasEmphasis;

    /**
     * 主题
     */
    private String title;

    /**
     * 发送时间
     */
    private Date sendDate;

    /**
     * 匹配关键字在主题 0-不匹配 1-匹配
     */
    private Boolean hasMatchTitle;

    /**
     * 匹配关键字在内容 0-不匹配 1-匹配
     */
    private Boolean hasMatchContent;

    /**
     * 匹配关键字在附件 0-不匹配 1-匹配
     */
    private Boolean hasMatchAttachment;

    /**
     * 匹配分类
     */
    private String matchCategory;

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
