package com.islet.service.mail;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.domain.vo.mail.TaskMailListVO;
import com.islet.model.mail.Information;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
public interface IInformationService extends IService<Information> {

    /**
     * 邮件列表
     * @param taskId
     * @return
     */
    List<TaskMailListVO> mailList(Long taskId);
}
