package com.islet.support.elasticsearch.service;


import com.islet.support.elasticsearch.domain.EmailEs;

/**
 * es邮件操作业务接口
 *
 * @author tangJM.
 * @date 2021/11/03
 */
public interface IEmailTransClientService {

    /**
     * 保存邮件
     * @param emailEs
     * @return
     */
    EmailEs saveEmail(EmailEs emailEs);
}
