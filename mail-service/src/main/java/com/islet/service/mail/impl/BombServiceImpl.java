package com.islet.service.mail.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.mapper.mail.BombMapper;
import com.islet.model.mail.Bomb;
import com.islet.service.mail.IBombService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-11
 */
@Service
public class BombServiceImpl extends ServiceImpl<BombMapper, Bomb> implements IBombService {

}
