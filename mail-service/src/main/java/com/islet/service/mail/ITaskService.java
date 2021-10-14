package com.islet.service.mail;
import com.islet.domain.dto.mail.TaskSaveDTO;

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
     * 新增邮箱
     * @param dto
     */
    Long saveTask(TaskSaveDTO dto);
}
