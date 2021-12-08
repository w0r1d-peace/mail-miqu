package com.islet.domain.dto.base;

import com.islet.domain.dto.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author tangJM.
 * @date 2021/12/8
 * @description
 */
@Data
public class RoleSavaOrUpdateDTO extends BaseDTO {

    private Long id;
    @NotBlank(message = "角色名不能为空")
    private String name;
    private String description;
    @NotNull(message = "请至少选择一个权限")
    private Set<Long> permissionIds;
}
