package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.mapper.mail.HostMapper;
import com.islet.model.mail.Host;
import com.islet.service.mail.IHostService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件服务器查询表，后期做成远程更新，供查询服务器主机和端口。exchange无需设置，有自动寻找的功能。不再使用nslookup去对比查询。 服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Service
public class HostServiceImpl extends ServiceImpl<HostMapper, Host> implements IHostService {

}
