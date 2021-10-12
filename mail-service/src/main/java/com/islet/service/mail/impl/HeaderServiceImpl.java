package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.mapper.mail.HeaderMapper;
import com.islet.model.mail.Header;
import com.islet.service.mail.IHeaderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件头 服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Service
public class HeaderServiceImpl extends ServiceImpl<HeaderMapper, Header> implements IHeaderService {

}
