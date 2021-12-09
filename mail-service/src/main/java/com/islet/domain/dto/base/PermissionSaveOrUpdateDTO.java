package com.islet.domain.dto.base;

import com.islet.domain.dto.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author tangJM.
 * @date 2021/12/9
 * @description
 */
@Data
public class PermissionSaveOrUpdateDTO extends BaseDTO {

    private Long id;
    private Long parentId;
    private String parentName;
    @NotBlank(message = "权限名称不能为空")
    private String name;
    @NotBlank(message = "唯一标识不能为空")
    private String key;
    @NotBlank(message = "路径不能为空")
    private String url;
    @NotNull(message = "类型不能为空")
    private Integer type;
    private String description;
}
