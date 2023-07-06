package com.islet.controller.mail;

import com.islet.common.web.Result;
import com.islet.controller.AbstractController;
import com.islet.domain.dto.mail.ExportDTO;
import com.islet.domain.dto.mail.TaskSaveOrUpdateDTO;
import com.islet.domain.vo.mail.TaskListVO;
import com.islet.domain.vo.mail.TaskMailDetailVO;
import com.islet.domain.vo.mail.TaskMailListVO;
import com.islet.service.mail.IInformationService;
import com.islet.service.mail.ITaskService;
import com.islet.support.elasticsearch.service.IEmailTransClientService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tangJM.
 * @date 2021/10/11
 * @description
 */
@Controller
@RequestMapping("/task")
@Validated
public class TaskController extends AbstractController {

    @Resource
    private ITaskService taskService;

    @Resource
    private IInformationService informationService;

    @Resource
    private IEmailTransClientService emailTransClientService;

    /**
     * 保存
     * @param form
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Result<Long> saveTask(@RequestBody @Valid TaskSaveOrUpdateDTO form) {
        return Result.success(taskService.saveTask(form));
    }

    /**
     * 编辑
     * @param form
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result<Boolean> updateTask(@RequestBody @Valid TaskSaveOrUpdateDTO form) {
        return Result.success(taskService.updateTask(form));
    }

    /**
     * 拉取邮件
     */
    @GetMapping("/pull/email")
    @ResponseBody
    public void pullEmail(@RequestParam @Valid List<Long> ids) {
        taskService.pullEmail(ids, super.getUserId(), super.getCreateName());
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteTask(@RequestBody @NotNull(message = "请选择删除邮箱") List<Long> ids) {
        return Result.success(taskService.deleteTask(ids, super.getUserId(), super.getCreateName()));
    }

    /**
     * 是否关注目标
     * */
    @PostMapping("/emphasis")
    @ResponseBody
    public Result<Boolean> emphasis(@RequestBody @NotNull(message = "主键不能为空") Long id) {
        return Result.success(taskService.emphasis(id, super.getUserId(), super.getCreateName()));
    }

    /**
     * 是否监控
     * */
    @PostMapping("/monitoring")
    @ResponseBody
    public Result<Boolean> monitoring(@RequestBody @NotNull(message = "主键不能为空") Long id) {
        return Result.success(taskService.monitoring(id, super.getUserId(), super.getCreateName()));
    }

    /**
     * 邮箱列表
     * @param email
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<List<TaskListVO>> list(String email) {
        return Result.success(taskService.list(email, super.getUserId()));
    }

    /**
     * 获取邮件列表
     */
    @GetMapping("mail/list")
    @ResponseBody
    public Result<List<TaskMailListVO>> mailList(@NotNull(message = "ID不能为空") Long id) {
        return Result.success(informationService.mailList(id));
    }

    /**
     * 获取邮件详情
     */
    @GetMapping("mail/detail")
    @ResponseBody
    public Result<TaskMailDetailVO> mailDetail(@NotNull(message = "ID不能为空") Long id) {
        return null;
    }

    /**
     * 导出
     * @return
     */
    @PostMapping("export")
    @ResponseBody
    public Result<Boolean> export(@RequestBody @Valid ExportDTO dto) {
        return Result.success(taskService.export(dto));
    }

}
