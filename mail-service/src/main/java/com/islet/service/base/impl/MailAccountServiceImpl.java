package com.islet.service.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.mapper.base.MailAccountMapper;
import com.islet.model.base.MailAccount;
import com.islet.service.base.IMailAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2022-01-04
 */
@Service
public class MailAccountServiceImpl extends ServiceImpl<MailAccountMapper, MailAccount> implements IMailAccountService {

}
