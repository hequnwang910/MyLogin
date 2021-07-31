package com.message.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.message.server.entity.User;
import com.message.server.entity.vo.UserAll;
import com.message.server.entity.vo.loginUser;
import com.message.server.mapper.UserMapper;
import com.message.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.server.utils.JwtUtils;
import com.message.server.utils.MD5;
import com.message.server.utils.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ahuwhq
 * @since 2021-07-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户名
     */
    @Override
    public List<UserAll> getAllUserName() {

        //创建一个构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询所有的userName
        wrapper.eq("userName",0);
        //重新封装一个UserAll的类，专门来查询所有用户名
        List<User> users = baseMapper.selectList(wrapper);

        List<UserAll> userAlls = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (StringUtils.isNoneEmpty(user.getUserName())){
                String userName = user.getUserName();
                UserAll userAll = new UserAll();
//                userAll.setUserName(userName);
                BeanUtils.copyProperties(user,userAll);
                userAlls.add(userAll);

            }
        }
        return userAlls ;
    }

    /**
     * 修改密码
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Boolean changePassword(String userName,String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName",userName);
        User user = baseMapper.selectOne(wrapper);
        if (user == null){
            return false;
        }
        user.setUserPassword(password);
        boolean save = userService.update(user,null);
        return save;
    }

//    /**
//     * 用户的登录功能
//     * @param userName
//     * @param password
//     * @return
//     */
//    @Override
//    public Boolean login(String userName, String password) {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("userName",userName);
//        wrapper.eq("userPassword",password);
//        Integer result = baseMapper.selectCount(wrapper);
//        if (result>0){
//            return true;
//        }else {
//            return false;
//        }
//    }

    /**
     * 用户的登录功能PostMapping
     * @param loginuser
     * @return
     */
    @Override
    public String login(loginUser loginuser) {
        String userName = loginuser.getUserName();
        String userPassword = loginuser.getUserPassword();
        //用户名和密码非空判断
        if(org.springframework.util.StringUtils.isEmpty(userName) || org.springframework.util.StringUtils.isEmpty(userPassword)) {
            return "wrong Password";
        }
        //判断手机号是否正确
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName",userName);
        User user = baseMapper.selectOne(wrapper);
        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!userPassword.equals(user.getUserPassword())) {
            return "wrong Password";

        }
        else {
            return "success login";
        }
//        //登录成功
//        //生成token字符串，使用jwt工具类
//        String jwtToken = JwtUtils.getJwtToken(user.getUserId().toString(), user.getNickName());
//        return jwtToken;

    }




    /**
     * 根据用户名称查询用户昵称
     * @param username
     * @return
     */
    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName",username);
        User user = baseMapper.selectOne(wrapper);
        return user;
    }



}
