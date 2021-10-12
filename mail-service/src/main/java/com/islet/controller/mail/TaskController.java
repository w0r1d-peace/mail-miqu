package com.islet.controller.mail;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.islet.common.Result;
import com.islet.domain.dto.mail.TaskSaveDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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



    @PostMapping("/save_task")
    @ResponseBody
    public Result saveTask(@Valid TaskSaveDTO form) {
        // 判断是否存在邮箱已录入
       /* int count = taskService.count(new LambdaQueryWrapper<Task>()
                .eq(Task::getEmail, form.getEmail())
                .eq(Task::getUserId, form.getUserId())
        );
        if (count > 0) {
            return new ApiResult().error(ResultEnum.EMAIL_EXIST_ALREADY);
        }
        taskService.saveTask(form);
        return new ApiResult().success();*/
        return null;
    }
}
