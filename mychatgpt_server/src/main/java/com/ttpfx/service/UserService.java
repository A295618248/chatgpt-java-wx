package com.ttpfx.service;

import com.ttpfx.entity.User;

/**
 * @author ttpfx
 * @date 2023/3/29
 */
public interface UserService{


    User queryByName(String username);

    User findByPhone(String phone);

    void save(User user);
}
