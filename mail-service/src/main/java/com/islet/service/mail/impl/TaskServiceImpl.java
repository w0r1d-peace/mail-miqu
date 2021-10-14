package com.islet.service.mail.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.exception.BusinessException;
import com.islet.domain.dto.mail.TaskSaveDTO;
import com.islet.enums.ConnStatusEnum;
import com.islet.enums.TaskProtocolTypeEnum;
import com.islet.mapper.mail.TaskMapper;
import com.islet.model.mail.Task;
import com.islet.service.mail.ITaskService;
import com.islet.service.mail.handler.server.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

/**
 * <p>
 * 邮件任务池，每个用户最多只有20条数据 服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Service
@Slf4j
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Override
    public Long saveTask(TaskSaveDTO dto) {
        // 判断邮箱是否已录入
        int count = super.count(new LambdaQueryWrapper<Task>()
                .eq(Task::getEmail, dto.getEmail())
                .eq(Task::getUserId, dto.getUserId())
                .eq(Task::getRemoved, false)
        );
        if (count > 0) {
            throw new BusinessException(String.format("邮箱%s已录入,请勿重复添加", dto.getEmail()));
        }

        // 连接测试
        MailConnCfg mailConnCfg = new MailConnCfg();
        BeanUtils.copyProperties(dto, mailConnCfg);
        mailConnCfg.setSsl(Optional.ofNullable(dto.getHasSsl()).orElse(false));
        connTest(mailConnCfg, dto.getType());

        Date now = new Date();
        Task task = new Task();
        BeanUtils.copyProperties(dto, task);
        task.setCreateTime(now);
        task.setModified(now);
        task.setRemoved(Boolean.FALSE);
        task.setConnStatus(ConnStatusEnum.CONN_NORMAL.getConnStatus());
        task.setReadNumber(0);  //已读数量
        task.setUnReadNumber(0);    //未读数量
        task.setSensitiveNumber(0); //敏感数量
        //保存数据
        this.save(task);
        return task.getId();
    }

    /**
     * 连接测试
     * @param mailConnCfg
     */
    private void connTest(MailConnCfg mailConnCfg, int protocolType) {
        try {
            IMailService mailService = getMailService(protocolType);
            mailService.createConn(mailConnCfg, false);
        } catch (Exception e) {
            String connExceptionReason = String.format("邮箱协议为【%s】邮箱连接失败，原始错误信息为【%s】", protocolType, e.getMessage());
            log.error(connExceptionReason);
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 获取mailService
     * @param protocolType
     * @return
     */
    public IMailService getMailService(int protocolType) {
        if (protocolType == TaskProtocolTypeEnum.POP3.getProtocolType()) {
            return new Pop3Service();
        } else if (protocolType == TaskProtocolTypeEnum.IMAP.getProtocolType()) {
            return new ImapService();
        } else if (protocolType == TaskProtocolTypeEnum.EXCHANGE.getProtocolType()) {
            return new MyExchangeService();
        } else {
            return null;
        }
    }
}
