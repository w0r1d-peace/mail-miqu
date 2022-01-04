package com.islet.service.mail;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.domain.dto.mail.TrojanPageDTO;
import com.islet.domain.dto.mail.TrojanSendDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.mail.TrojanPageVO;
import com.islet.model.mail.Trojan;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
public interface ITrojanService extends IService<Trojan> {

    /**
     * 列表
     * @param dto
     * @return
     */
    PageVO<TrojanPageVO> list(TrojanPageDTO dto);

    /**
     * 发送邮件
     * @param dto
     * @return
     */
    Boolean send(TrojanSendDTO dto);
}
