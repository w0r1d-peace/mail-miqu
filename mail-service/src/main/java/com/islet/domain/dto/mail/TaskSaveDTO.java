package com.islet.domain.dto.mail;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author tangJM.
 * @date 2021/10/11
 * @description
 */
@Data
public class TaskSaveDTO {
    /**
     * 邮箱
     */
    @NotEmpty(message = "目标邮箱不能为空")
    @Email(message = "邮箱格式有误")
    private String email;

    /**
     * 密码
     */
    @NotEmpty(message = "邮箱密码不能为空")
    private String password;

    /**
     * 协议类型
     */
    private Integer protocolType;

    /**
     * 服务器地址
     */
    private String host;

    /**
     * 服务器端口
     */
    private Integer port;

    /**
     * 是否使用SSL
     */
    private Boolean hasSsl;

    /**
     * 是否监控
     */
    @NotNull(message = "是否监控不能为空")
    private Boolean hasMonitoring;

    /**
     * 是否为重点关注目标
     */
    @NotNull(message = "重点关注目标不能为空")
    private Boolean hasEmphasis;

    /**
     * 备注
     */
    private String description;
}
