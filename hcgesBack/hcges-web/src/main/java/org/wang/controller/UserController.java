package org.wang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wang.model.Request.RegisterRequest;
import org.wang.model.User;
import org.wang.service.UnitService;
import org.wang.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 注册
    @PostMapping ("/userRegister")
    public RegisterRequest hello(@RequestBody User user){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",user.getUsername());

        RegisterRequest request = new RegisterRequest();

        if (userService.count(queryWrapper) > 0){
            request.setCode(0);
            request.setRMsg("名称已存在");
            return request ;
        }

        userService.userRegister(user.getUsername(), "123456", user.getReal_name(), "0");

        request.setCode(1);
        request.setRMsg("注册成功");

        return request;

    }

    // 查询人员
    @GetMapping("/getUser")
    private IPage<User> getUser(@RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer pageSize){
        IPage<User> iPage = new Page<>(page,pageSize);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",keyword);

        return userService.getUser(iPage,queryWrapper);
    }

    // 删除人员
    @GetMapping("/deleteUser")
    private void deleteUser(@RequestParam String id){
        userService.deleteById(id);
    }

    // 修改人员信息
    @PostMapping("/updateUser")
    private void updateUser(@RequestBody User user){
        userService.updateById(user.getID(),user.getUsername(),user.getPassword(),user.getReal_name(),user.getState());
    }
}
