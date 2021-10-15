package com.islet.domain.dto.mail;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author tangJM.
 * @date 2021/10/11
 * @description
 */
@Data
public class TaskSaveOrUpdateDTO {

    /**
     * 主键
     */
    private Long id;

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
     * 类型
     */
    @NotNull(message = "类型不能为空")
    private Integer type;

    /**
     * 收件服务器
     */
    @NotBlank(message = "收件服务器不能为空")
    private String host;

    /**
     * 服务器端口
     */
    @NotNull(message = "端口不能为空")
    private Integer port;

    /**
     * 是否使用SSL
     */
    private Boolean hasSsl;

    /**
     * 是否监控
     */
    private Boolean monitoring;

    /**
     * 是否为重点关注目标
     */
    private Boolean emphasis;

    /**
     * 备注
     */
    private String description;
}
