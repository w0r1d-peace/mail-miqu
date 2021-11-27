package com.islet.support.elasticsearch.service.impl;

import com.islet.support.elasticsearch.domain.EmailEs;
import com.islet.support.elasticsearch.repository.EmailEsRepository;
import com.islet.support.elasticsearch.service.IEmailTransClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tangJM.
 * @date 2021/11/03
 */
@Service
@Slf4j
public class EmailTransClientServiceImpl implements IEmailTransClientService {

    @Resource
    private EmailEsRepository repository;

    @Override
    public EmailEs saveEmail(EmailEs emailEs) {
        return repository.save(emailEs);
    }

    @Override
    public void getMailContent(Long emailId) {

    }
}
