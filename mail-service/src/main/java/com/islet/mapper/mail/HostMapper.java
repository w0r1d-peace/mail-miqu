package com.islet.mapper.mail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.islet.model.mail.Host;

/**
 * <p>
 * 邮件服务器查询表，后期做成远程更新，供查询服务器主机和端口。exchange无需设置，有自动寻找的功能。不再使用nslookup去对比查询。 Mapper 接口
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
public interface HostMapper extends BaseMapper<Host> {

}
