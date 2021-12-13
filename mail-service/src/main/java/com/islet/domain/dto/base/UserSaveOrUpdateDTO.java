package com.islet.domain.dto.base;

import com.islet.domain.dto.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author tangJM.
 * @date 2021/12/7
 * @description
 */
@Data
public class UserSaveOrUpdateDTO extends BaseDTO {

    private Long id;
    @NotBlank(message = "真实姓名不能为空")
    private String name;
    @NotBlank(message = "手机号码不能为空")
    private String phone;
    private String username;
    private String password;
    @NotNull(message = "请选择一个所属角色")
    private Long roleId;
    private String description;
}
