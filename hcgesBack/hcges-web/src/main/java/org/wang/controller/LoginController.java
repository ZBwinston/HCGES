package org.wang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wang.model.Log;
import org.wang.model.Request.DoLoginRequest;
import org.wang.model.User;
import org.wang.service.LogService;
import org.wang.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    // 登录
    @PostMapping("/doLogin")
    public User doLogin(@RequestBody DoLoginRequest doLoginRequest, HttpServletRequest request){
        if (doLoginRequest == null){
            return null;
        }
        String username = doLoginRequest.getUsername();
        String password = doLoginRequest.getPassword();

        Log log = new Log();
        log.setAction_time(new Date());
        log.setOperator(username);
        log.setAction_content("登录");

        logService.addLog(log);

        return userService.doLogin(username, password, request);
    }

    // 查询菜单
    @GetMapping("/checkMenu")
    public void checkMenu(HttpServletRequest request){
        request.getSession().getAttribute("userloginmenu");
    }

    // 修改信息
    @PostMapping("/updateById")
    public ResponseEntity<String> updateById(@RequestBody Map<String, String> formData){

        String account = formData.get("account");
        String password1 = formData.get("password1");
        String password2 = formData.get("password2");

        // 检查密码是否匹配
        if (!password1.equals(password2)) {
            return ResponseEntity.badRequest().body("两次输入的密码不匹配");
        }

        // 检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("ID",account);

        Long aLong = userService.selectCount(queryWrapper);

        if (aLong == 1){
            userService.updatePassword(account,password1);
            return ResponseEntity.ok("密码修改成功，请重新登录");
        }

        return ResponseEntity.badRequest().body("修改密码失败，账号不存在");
    }


}
