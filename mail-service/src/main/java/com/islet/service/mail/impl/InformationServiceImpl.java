package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.mapper.mail.InformationMapper;
import com.islet.model.mail.Information;
import com.islet.service.mail.IInformationService;
import org.springframework.stereotype.Service;

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

}
