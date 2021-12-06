package com.islet.domain.vo.bese;

import lombok.Data;
import java.util.Date;

/**
 * @author tangJM.
 * @date 2021/12/6
 * @description
 */
@Data
public class UserPageVO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;
}
