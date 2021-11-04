package com.islet.service.mail;
import com.islet.domain.dto.mail.TaskSaveOrUpdateDTO;
import com.islet.domain.vo.mail.TaskListVO;

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

    /**
     * 删除任务
     * @param ids
     * @return
     */
    Boolean deleteTask(List<Long> ids, Long userId, String createName);

    /**
     * 是否关注目标
     * @param id
     * @param userId
     * @param createName
     * @return
     */
    Boolean emphasis(Long id, Long userId, String createName);

    /**
     * 是否监控
     * @param id
     * @param userId
     * @param createName
     * @return
     */
    Boolean monitoring(Long id, Long userId, String createName);

    /**
     * 任务列表
     * @param email
     * @param userId
     * @return
     */
    List<TaskListVO> list(String email, Long userId);
}

