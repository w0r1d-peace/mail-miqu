package com.islet.mapper.mail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.islet.model.mail.Task;

/**
 * <p>
 * 邮件任务池，每个用户最多只有20条数据 Mapper 接口
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
public interface TaskMapper extends BaseMapper<Task> {

}
