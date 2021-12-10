package com.islet.domain.dto.base;

import com.islet.domain.dto.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author tangJM.
 * @date 2021/12/7
 * @description
 */
@Data
public class UserSaveOrUpdateDTO extends BaseDTO {

    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String name;
    private String phone;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotNull(message = "请至少选择一个所属角色")
    private Set<RoleIdNameDTO> roleList;
    @NotBlank(message = "真实姓名不能为空")
    private String description;
}
