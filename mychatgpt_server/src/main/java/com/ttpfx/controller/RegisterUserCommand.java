package com.ttpfx.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterUserCommand {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码必填")
    private String password;
    /**
     * 电话号码
     */
    @NotBlank(message = "手机号必填")
    private String phone;
    /**
     * 用户来源
     */
    private Source source;
}
