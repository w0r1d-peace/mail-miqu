package com.islet.domain.vo.bese;

import lombok.Data;

import java.util.Set;

/**
 * @author tangJM.
 * @date 2021/12/8
 * @description
 */
@Data
public class RolePageVO {

    private Long id;
    private String name;
    private Integer userNumber;
    private String description;

    private Set<Long> permissionIds;
}
