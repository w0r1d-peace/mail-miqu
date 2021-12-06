package com.islet.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.domain.dto.base.UserLoginDTO;
import com.islet.domain.dto.base.UserPageDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.UserPageVO;
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

    /**
     * 用户详情
     * @param id
     * @return
     */
    User detail(Long id);

    /**
     * 登录
     * @param dto
     * @param token
     */
    void login(UserLoginDTO dto, String token);

    /**
     * 用户列表（分页）
     * @param dto
     */
    PageVO<UserPageVO> userPage(UserPageDTO dto);
}
