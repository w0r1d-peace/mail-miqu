package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.mapper.mail.TaskMapper;
import com.islet.model.mail.Task;
import com.islet.service.mail.ITaskService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件任务池，每个用户最多只有20条数据 服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
