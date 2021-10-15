package com.islet.controller.mail;

import com.islet.common.web.Result;
import com.islet.domain.dto.mail.TaskSaveOrUpdateDTO;
import com.islet.service.mail.ITaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author tangJM.
 * @date 2021/10/11
 * @description
 */
@Controller
@RequestMapping("/task")
@Validated
public class TaskController {

    @Resource
    private ITaskService taskService;

    /**
     * 保存
     * @param form
     * @return
     */
    @PostMapping("/save_task")
    @ResponseBody
    public Result<Long> saveTask(@RequestBody @Valid TaskSaveOrUpdateDTO form) {
        return Result.success(taskService.saveTask(form));
    }

    /**
     * 编辑
     * @param form
     * @return
     */
    @PostMapping("/update_task")
    @ResponseBody
    public Result<Boolean> updateTask(@RequestBody @Valid TaskSaveOrUpdateDTO form) {
        return Result.success(taskService.updateTask(form));
    }

    /**
     * 拉取邮件
     */
    @GetMapping("/pull_email")
    @ResponseBody
    public void pullEmail(@RequestParam @Valid List<Long> ids) {
        taskService.pullEmail(ids);
    }
}
