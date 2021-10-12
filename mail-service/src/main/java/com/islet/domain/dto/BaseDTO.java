package com.islet.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liqigui
 * @date 2020-09-18 10:06
 */
@Data
@ToString
public class BaseDTO implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建人
     */
    private String creator;
}
