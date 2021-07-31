package com.message.server.controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.message.server.entity.User;
import com.message.server.entity.vo.UserAll;
import com.message.server.entity.vo.loginUser;
import com.message.server.service.UserService;
import com.message.server.utils.AESHelper;
import com.message.server.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ahuwhq
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/server/user")
public class UserController {
    //访问地址：http://114.213.208.85:8001/server/user/findAll    最终部署到服务器时使用这个来访问
    //访问地址：http://localhost:8001/server/user/findAll
    @Autowired
    private UserService userService;



    /**
     * 查询所有的用户
     * @return
     */
    @GetMapping("getAllUsers")
    public List<User> findAllUser(){
        //调用方法查询
        List<User> list = userService.list(null);
        return list;
    }

    /**
     * 添加用户,注册功能
     * @param user
     * @return
     */
    @PostMapping("addUser")
    public Boolean addUser(@RequestBody User user){
        boolean save = userService.save(user);
        return save;

    }

    /**
     * 获取所有用户名
     */
    @GetMapping("getAllUserName")
    public List<UserAll> getAllUserName(){
        List<UserAll> userNamelist = userService.getAllUserName();
        return userNamelist;
    }


    /**
     * 根据用户ID查询用户的昵称
     * @param id
     * @return
     */
    @GetMapping("getUserNickname/{id}")
    public String getUserNickname(@PathVariable Integer id){
        User user = userService.getById(id);
        String nickName = user.getNickName();
        return nickName;
    }


    /**
     * 根据用户名称查询用户昵称
     * @param username
     * @return
     */
    @GetMapping("getUserNicknameByname/{username}")
    public String getUserNicknameByname(@PathVariable String username){
        User user = userService.getByUsername(username);
        String nickName = user.getNickName();
        return nickName;
    }

    /**
     * 根据用户名称查询用户昵称使用retrofit ----GetMapping
     * @param username
     * @return
     */
    @GetMapping("getUserNicknameBynameByretrofitget")
    public String getUserNicknameBynameByretrofitByGet(String username){
        User user = userService.getByUsername(username);
        String nickName = user.getNickName();
        return nickName;
    }

    /**
     * 根据用户名称查询用户昵称使用retrofit ----PostMapping
     * @param username
     * @return
     */
    @PostMapping("getUserNicknameBynameByretrofitpost")
    public String getUserNicknameBynameByretrofitByPost(String username){
        User user = userService.getByUsername(username);
        String nickName = user.getNickName();
        return nickName;
    }




    /**
     *修改密码
     * @param userName
     * @return
     */
    @PostMapping("changePassword/{userName}/{password}")
    public Boolean changePassword(@PathVariable String userName,
                                  @PathVariable String password){
        Boolean result = userService.changePassword(userName,password);
        return result;
    }


//    /**
//     * 用户的登录功能GetMapping
//     * @param userName
//     * @param password
//     * @return
//     */
//    @GetMapping("login/{userName}/{password}")
//    public Boolean login(@PathVariable String userName,
//                         @PathVariable String password){
//        Boolean result = userService.login(userName,password);
//        return result;
//    }

    /**
     * 用户的登录功能PostMapping
     * @param loginuser
     * @return
     */
    @PostMapping("login")
    public R login(@RequestBody loginUser loginuser){
        System.out.println("loginuser"+loginuser);
        String token = userService.login(loginuser);
        if (token == "wrong Password"){
            return R.error().data("result",token);
        }else{
            return R.ok().data("result",token);
        }
    }


    @PostMapping("loginaes")
    public R loginAES(@RequestBody String Aes){
        String decrypt = AESHelper.decrypt(Aes,"111");
        System.out.println("decrypt"+decrypt);
        Gson gson = new GsonBuilder().create();
        loginUser loginUser = gson.fromJson(decrypt, loginUser.class);
        System.out.println("loginUser"+loginUser.toString());
        System.out.println("loginUser"+loginUser);
        String token = userService.login(loginUser);
        if (token == "wrong Password"){
            return R.error().data("result",token);
        }else{
            return R.ok().data("result",token);
        }
    }



}

