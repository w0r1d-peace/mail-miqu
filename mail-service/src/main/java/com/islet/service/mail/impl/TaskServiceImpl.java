package com.islet.service.mail.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.domain.dto.mail.TaskSaveOrUpdateDTO;
import com.islet.enums.ConnStatusEnum;
import com.islet.enums.TaskProtocolTypeEnum;
import com.islet.exception.BusinessException;
import com.islet.exception.MailPlusException;
import com.islet.mapper.mail.TaskMapper;
import com.islet.model.mail.Task;
import com.islet.service.mail.ITaskService;
import com.islet.service.mail.handler.server.*;
import com.islet.support.elasticsearch.domain.EmailEs;
import com.islet.support.elasticsearch.service.IEmailTransClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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

    @Resource
    private IEmailTransClientService emailTransClientService;

    @Override
    public Long saveTask(TaskSaveOrUpdateDTO dto) {
        // 判断邮箱是否已录入
        int count = super.count(new LambdaQueryWrapper<Task>()
                .eq(Task::getEmail, dto.getEmail())
              //  .eq(Task::getCreateId, dto.getUserId())
                .eq(Task::getRemoved, false)
        );
        if (count > 0) {
            throw new BusinessException(String.format("邮箱%s已录入,请勿重复添加", dto.getEmail()));
        }

        // 连接测试
        MailConnCfg mailConnCfg = new MailConnCfg();
        BeanUtils.copyProperties(dto, mailConnCfg);
        mailConnCfg.setSsl(Optional.ofNullable(dto.getHasSsl()).orElse(false));
        IMailService mailService = getMailService(dto.getType());
        getMailConn(mailConnCfg, mailService);

        Date now = new Date();
        Task task = new Task();
        BeanUtils.copyProperties(dto, task);
        /*task.setCreateId(dto.getUserId());
        task.setCreateName(dto.getCreator());*/
        task.setCreateTime(now);
        /*task.setUpdateId(dto.getUserId());
        task.setUpdateName(dto.getCreator());*/
        task.setUpdateTime(now);
        task.setRemoved(Boolean.FALSE);
        task.setConnStatus(ConnStatusEnum.CONN_NORMAL.getConnStatus());
        task.setReadNumber(0);  //已读数量
        task.setUnReadNumber(0);    //未读数量
        task.setSensitiveNumber(0); //敏感数量
        //保存数据
        this.save(task);
        return task.getId();
    }

    @Override
    public Boolean updateTask(TaskSaveOrUpdateDTO dto) {
        // 判断邮箱是否已录入
       /* int count = super.count(new LambdaQueryWrapper<Task>()
                .ne(Task::getId, dto.getUserId())
                .eq(Task::getEmail, dto.getEmail())
                .eq(Task::getCreateId, dto.getUserId())
                .eq(Task::getRemoved, false)
        );
        if (count > 0) {
            throw new BusinessException(String.format("邮箱%s已录入,请勿重复添加", dto.getEmail()));
        }*/

        // 连接测试
        MailConnCfg mailConnCfg = new MailConnCfg();
        BeanUtils.copyProperties(dto, mailConnCfg);
        mailConnCfg.setSsl(Optional.ofNullable(dto.getHasSsl()).orElse(false));
        IMailService mailService = getMailService(dto.getType());
        getMailConn(mailConnCfg, mailService);

        Task task = getById(dto.getId());
        BeanUtils.copyProperties(dto, task);
/*        task.setUpdateId(dto.getUserId());
        task.setUpdateName(dto.getCreator());*/
        task.setUpdateTime(new Date());
        return super.updateById(task);
    }

    @Override
    public void pullEmail(List<Long> ids) {
        List<Task> taskList = super.list(new LambdaQueryWrapper<Task>().in(Task::getId, ids));
        if (taskList != null) {
            taskList.stream().forEach(task -> {
                MailConnCfg mailConnCfg = new MailConnCfg();
                BeanUtils.copyProperties(task, mailConnCfg);
                IMailService mailService = getMailService(task.getType());
                MailConn mailConn = getMailConn(mailConnCfg, mailService);
                try {
                    List<MailItem> mailItems = mailService.listAll(mailConn, "", null);
                    for (MailItem mailItem : mailItems) {
                        UniversalMail universalMail = mailService.parseEmail(mailItem, "C:/Users/EDZ/Desktop/email/");
                        EmailEs emailEs = new EmailEs();
                        BeanUtils.copyProperties(universalMail, emailEs);
                        emailTransClientService.saveEmail(emailEs);
                        log.info(universalMail.getUid());
                    }
                } catch (MailPlusException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public Boolean deleteTask(List<Long> ids, Long userId, String createName) {
        return super.update(new LambdaUpdateWrapper<Task>()
                .set(Task::getRemoved, true)
                .set(Task::getUpdateId, userId)
                .set(Task::getUpdateName, createName)
                .set(Task::getUpdateTime, new Date())
                .in(Task::getId, ids)
                .eq(Task::getCreateId, userId)
        );
    }

    @Override
    public Boolean emphasis(Long id, Long userId, String createName) {
        return super.update(new LambdaUpdateWrapper<Task>()
                .set(Task::getEmphasis, true)
                .set(Task::getUpdateId, userId)
                .set(Task::getUpdateName, createName)
                .set(Task::getUpdateTime, new Date())
                .eq(Task::getId, id)
                .eq(Task::getCreateId, userId)
        );
    }

    @Override
    public Boolean monitoring(Long id, Long userId, String createName) {
        return super.update(new LambdaUpdateWrapper<Task>()
                .set(Task::getMonitoring, true)
                .set(Task::getUpdateId, userId)
                .set(Task::getUpdateName, createName)
                .set(Task::getUpdateTime, new Date())
                .eq(Task::getId, id)
                .eq(Task::getCreateId, userId)
        );
    }

    /**
     * 获取
     * @param mailConnCfg
     */
    private MailConn getMailConn(MailConnCfg mailConnCfg, IMailService mailService) {
        try {
            MailConn mailConn = mailService.createConn(mailConnCfg, false);
            return mailConn;
        } catch (Exception e) {
            String connExceptionReason = String.format("邮箱连接失败，原始错误信息为【%s】", e.getMessage());
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
