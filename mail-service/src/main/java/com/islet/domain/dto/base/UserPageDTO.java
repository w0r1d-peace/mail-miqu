package com.islet.domain.dto.base;

import com.islet.domain.dto.PageDTO;
import lombok.Data;

/**
 * @author tangJM.
 * @date 2021/12/6
 * @description
 */
@Data
public class UserPageDTO extends PageDTO {
    private String username;
    private String name;
    private String phone;
    private Long roleId;
}
