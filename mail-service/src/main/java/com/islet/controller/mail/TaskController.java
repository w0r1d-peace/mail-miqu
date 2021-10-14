package com.islet.controller.mail;

import com.islet.common.web.Result;
import com.islet.domain.dto.mail.TaskSaveDTO;
import com.islet.service.mail.ITaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    public Result<Long> saveTask(@RequestBody @Valid TaskSaveDTO form) {
        return Result.success(taskService.saveTask(form));
    }

    /**
     * 编辑
     * @param form
     * @return
     */
    @PostMapping("/edit_task")
    @ResponseBody
    public Result<Long> editTask(@RequestBody @Valid TaskSaveDTO form) {
        return Result.success(taskService.saveTask(form));
    }
}
