package com.islet.service.mail.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.domain.dto.mail.TrojanPageDTO;
import com.islet.domain.dto.mail.TrojanSendDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.mail.TrojanPageVO;
import com.islet.mapper.mail.TrojanMapper;
import com.islet.model.mail.Trojan;
import com.islet.service.mail.ITrojanService;
import com.islet.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Date;
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
public class TrojanServiceImpl extends ServiceImpl<TrojanMapper, Trojan> implements ITrojanService {

    @Override
    public PageVO<TrojanPageVO> list(TrojanPageDTO dto) {
        // 组装条件
        LambdaQueryWrapper<Trojan> queryWrapper = new QueryWrapper<Trojan>()
                .lambda()
                .eq(StringUtils.isNotEmpty(dto.getTarget()), Trojan::getTarget, dto.getTarget())
                .eq(dto.getStatus() != null, Trojan::getStatus, dto.getStatus())
                .between(StringUtils.isNotEmpty(dto.getStartTime()) && StringUtils.isNotEmpty(dto.getEndTime()), Trojan::getCreateTime, dto.getStartTime(), dto.getEndTime())
                .eq(Trojan::getUserId, dto.getUserId());


        return PageUtil.getPageVOByIPage(page -> (IPage<Trojan>) super.page(page
                , queryWrapper)
                , dto.getCurrentPage()
                , dto.getPageSize()
                , TrojanPageVO.class);
    }

    @Override
    public Boolean send(TrojanSendDTO dto) {
        /*// 获取到发件账号
        MailAccount mailAccount = mailAccountService.getById(form.getAccountId());
        if (mailAccount == null) {
            log.error("查询不到邮箱账号ID{}的记录", form.getAccountId());
            throw new ApiException(ResultEnum.UNKNOWN_ERR);
        }

        String fakeEmail = "";
        String fakeName = "";
        if (dto.getMailCounterfeitFlag()) {
            fakeEmail = form.getMailSploitEmail();
            fakeName = form.getMailSploitName();
        }

        CommonsMultipartFile file = null;
        String pathname = null;
        if (form.getFile() != null) {
            file = form.getFile();
            pathname = targetDir + "/" + file.getOriginalFilename();
        }

        //保存内容及木马附件
        TrojanDetail trojanDetail = new TrojanDetail();
        trojanDetail.setContent(form.getContent());
        trojanDetail.setAttachment(pathname);
        trojanDetail.setRemoved(0);
        trojanDetailService.save(trojanDetail);

        String [] targets = form.getTargets().split(";");
        List<Trojan> trojanList = new ArrayList<>();
        for (String target : targets) {
            Trojan trojan = Trojan.builder()
                    .accountId(mailAccount.getId()).source(mailAccount.getAccount())
                    .target(target).mailSploit(form.getMailSploit())
                    .mailSploitName(fakeName).mailSploitEmail(fakeEmail)
                    .vpnId(form.getVpnId())
                    .title(form.getTitle()).trojanDetailId(trojanDetail.getId())
                    .status(TrojanStatusEnum.WAITING.getStatus()).createTime(new Date())
                    .modified(new Date()).removed(0).userId(form.getUserId()).creator(form.getCreator())
                    .build();
            trojanList.add(trojan);
        }
        // 批量保存
        this.saveBatch(trojanList);
        //发送邮件
        send(trojanDetail, trojanList, file, mailAccount, proxyHost, proxyPort);*/
        return null;
    }
}
