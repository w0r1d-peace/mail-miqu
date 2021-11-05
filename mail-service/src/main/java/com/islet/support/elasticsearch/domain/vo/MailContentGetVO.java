package com.islet.support.elasticsearch.domain.vo;

import lombok.Data;

/**
 * @author tangJM.
 * @date 2021/11/5
 * @description
 */
@Data
public class MailContentGetVO {

    /**
     * 邮箱账号
     */
    private String email;

    /**
     * uid
     */
    private String uid;

    private String fromer;

    private String bcc;

    private String cc;
}
