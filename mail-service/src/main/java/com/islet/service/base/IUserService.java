package com.islet.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.model.base.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-09-18
 */
public interface IUserService extends IService<User> {

    User detail(Long id);
}
