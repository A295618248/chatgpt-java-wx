package com.ttpfx.controller;

import com.ttpfx.entity.User;
import com.ttpfx.service.UserService;
import com.ttpfx.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ttpfx
 * @date 2023/3/29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    public static ConcurrentHashMap<String, User> loginUser = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, Long> loginUserKey = new ConcurrentHashMap<>();

    @PostMapping
    public R register(RegisterUserCommand command){
        User user = userService.findByPhone(command.getPhone());
        if (user != null) {
            throw new RuntimeException("该手机号已经注册过了，请勿重复注册");
        }
        user = new User(command);
        userService.save(user);
        return R.ok("注册成功");
    }

    @RequestMapping("/login")
    public R login(String username, String password) {
        if (username == null) return R.fail("必须填写用户名");


        User user = userService.queryByName(username);
        if (user == null) return R.fail("用户名不存在");
        String targetPassword = user.getPassword();
        if (targetPassword == null) return R.fail("用户密码异常");
        if (!targetPassword.equals(password)) return R.fail("密码错误");

        loginUser.put(username, user);
        loginUserKey.put(username, System.currentTimeMillis());
        return R.ok(String.valueOf(loginUserKey.get(username)));
    }

    @RequestMapping("/logout")
    public R logout(String username) {
        loginUser.remove(username);
        loginUserKey.remove(username);
        return R.ok();
    }

    @RequestMapping("/checkUserKey")
    public R checkUserKey(String username, Long key){
        if (username==null || key == null)return R.fail("用户校验异常");
        if (!Objects.equals(loginUserKey.get(username), key)){
            return R.fail("用户在其他地方登录！！！");
        }
        return R.ok();
    }

    @RequestMapping("/loginUser")
    public R loginUser(){
        return R.ok("success",loginUser.keySet());
    }
}
