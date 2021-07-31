package com.message.server.service;

import com.message.server.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.message.server.entity.vo.UserAll;
import com.message.server.entity.vo.loginUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ahuwhq
 * @since 2021-07-27
 */
public interface UserService extends IService<User> {

    /**
     * 获取所有用户名
     * @return
     */
    List<UserAll> getAllUserName();

    /**
     * 修改密码
     * @param userName
     * @return
     */
    Boolean changePassword(String userName,String password);

//    /**
//     * 用户的登录功能
//     * @param userName
//     * @param password
//     * @return
//     */
//    Boolean login(String userName, String password);

    /**
     * 根据用户名称查询用户昵称
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 用户的登录功能PostMapping
     * @param loginuser
     * @return
     */
    String login(loginUser loginuser);


}
