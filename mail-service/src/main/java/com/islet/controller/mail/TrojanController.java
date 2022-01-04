package com.islet.controller.mail;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.islet.common.web.Result;
import com.islet.domain.dto.mail.TrojanPageDTO;
import com.islet.domain.dto.mail.TrojanSendDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.mail.TrojanPageVO;
import com.islet.model.mail.Trojan;
import com.islet.service.mail.ITrojanService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangJM.
 * @since 2019-06-28
 */
@Controller
@RequestMapping("/trojan")
@Validated
public class TrojanController {

    @Resource
    private ITrojanService trojanService;

    /**
     * 列表
     * */
    @PostMapping("/list")
    @ResponseBody
    public Result<PageVO<TrojanPageVO>> list(@Valid TrojanPageDTO dto) {
        PageVO<TrojanPageVO> page = trojanService.list(dto);
        return Result.success(page);
    }

    /**
     * （木马邮件）发送邮件
     * */
    @PostMapping("/send")
    @ResponseBody
    public Result<Boolean> send(@Valid TrojanSendDTO dto) {
        return Result.success(trojanService.send(dto));
    }

  /*  *//**
     * 重送邮件
     *//*
    @PostMapping("/mail/trojan/retry")
    @ResponseBody
    public ApiResult retry(@Valid TrojanRetryForm form, BindingResult bindingResult) {
        ParamUtil.checkRequestParams(bindingResult);
        trojanService.retrySendTrojan(form);
        return new ApiResult().success();
    }

    *//**
     * 删除
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     *//*
    @PostMapping("/mail/trojan/delete")
    @ResponseBody
    public ApiResult delete(@Valid TrojanDeleteForm form, BindingResult bindingResult) {
        ParamUtil.checkRequestParams(bindingResult);
        trojanService.remove(
                new LambdaQueryWrapper<Trojan>()
                        .in(Trojan::getId, form.getIds())
                        .eq(Trojan::getUserId, form.getUserId()));
        return new ApiResult().success();
    }*/

}
