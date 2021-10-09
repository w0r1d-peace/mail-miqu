package com.islet.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author tangJM.
 * @date 2021/10/9
 * @description
 */
@Data
public class UserLoginDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
