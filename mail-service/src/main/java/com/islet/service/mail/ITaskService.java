package com.islet.service.mail;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.model.mail.Task;

/**
 * <p>
 * 邮件任务池，每个用户最多只有20条数据 服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
public interface ITaskService extends IService<Task> {

}
