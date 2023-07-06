package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.domain.vo.mail.TaskMailListVO;
import com.islet.mapper.mail.InformationMapper;
import com.islet.model.base.User;
import com.islet.model.mail.Information;
import com.islet.service.mail.IInformationService;
import com.islet.util.CachedBeanCopierUtil;
import com.islet.util.ThreadUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements IInformationService {

    @Override
    public List<TaskMailListVO> mailList(Long taskId) {
        User user = ThreadUtil.currentUser.get();
        List<Information> list = super.list(new LambdaQueryWrapper<Information>().eq(Information::getRemoved, false).eq(Information::getTaskId, taskId).eq(Information::getUserId, user.getUserId()));
        return CachedBeanCopierUtil.copyList(list, TaskMailListVO.class);
    }
}
