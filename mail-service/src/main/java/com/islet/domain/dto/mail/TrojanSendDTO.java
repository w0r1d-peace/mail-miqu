package com.islet.domain.dto.mail;

import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author tangJM.
 * @date 2022/1/4
 * @description
 */
@Data
public class TrojanSendDTO {

    @NotNull(message = "发件人帐号必选")
    private Long accountId;
    @NotBlank(message = "目标邮箱必填")
    private String targets;
    @NotBlank(message = "标题必填")
    private String title;
    @NotBlank(message = "内容必填")
    private String content;
    /**
     * 是否开启仿冒邮箱
     */
    @NotNull(message = "是否使用仿冒邮箱漏洞必选")
    private Boolean mailCounterfeitFlag;

    /**
     * 仿冒名
     */
    private String mailCounterfeitName;

    /**
     * 仿冒邮箱
     */
    private String mailCounterfeitEmail;
    private CommonsMultipartFile file;
}
