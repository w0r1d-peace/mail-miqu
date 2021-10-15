package com.islet.service.mail;
import com.islet.domain.dto.mail.TaskSaveOrUpdateDTO;

import java.util.List;

/**
 * <p>
 * 任务 服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
public interface ITaskService {

    /**
     * 新增
     * @param dto
     */
    Long saveTask(TaskSaveOrUpdateDTO dto);

    /**
     * 编辑
     * @param dto
     * @return
     */
    Boolean updateTask(TaskSaveOrUpdateDTO dto);

    /**
     * 拉取邮件
     * @param ids
     * @return
     */
    void pullEmail(List<Long> ids);
}
