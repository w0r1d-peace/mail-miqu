package com.islet.domain.vo.bese;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd mm:HH:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;
}
