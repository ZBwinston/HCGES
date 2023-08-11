package org.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.wang.model.User;
import org.wang.service.UserService;
import org.wang.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author qwertyuiop
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-04-27 14:58:19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    final String SALT = "wang";

    private static final String USER_LOGIN_STATE = "userloginstate";

    @Resource
    private UserMapper userMapper;

    // 用户注册
    @Override
    public String userRegister(String username, String password, String real_name, String state) {
        String s = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        User user = new User();
        user.setUsername(username);
        user.setPassword(s);
        user.setReal_name(real_name);
        user.setState(state);
        boolean save = this.save(user);
        if (save) {
            return user.getID();
        }
        return null;
    }

    // 用户登录
    @Override
    public User doLogin(String username, String password, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(username, password)) {
            return null;
        }
        if (username.length() < 4) {
            return null;
        }
        if (password.length() < 6) {
            return null;
        }
        // 加密
        String s = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        // 查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", s);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, username cannot match password");
            return null;
        }

        // 用户脱敏
        User safetyUser = new User();
        safetyUser.setID(user.getID());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setPassword(user.getPassword());
        safetyUser.setReal_name(user.getReal_name());
        safetyUser.setState(user.getState());

        // 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);

        return user;
    }

    // 修改信息
    @Override
    public int updateById(String id, String username, String password, String real_name, String state) {

        String s = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        User user = new User();
        if (state != null){
            user.setID(id);
            user.setState(state);
        }else {
            user.setID(id);
            user.setUsername(username);
            user.setReal_name(real_name);
        }

        return userMapper.updateById(user);
    }

    // 查询用户
    @Override
    public IPage<User> getUser(IPage<User> iPage, QueryWrapper<User> queryWrapper) {
        return userMapper.selectPage(iPage,queryWrapper);
    }

    @Override
    public Long selectCount(QueryWrapper<User> queryWrapper) {
        return userMapper.selectCount(queryWrapper);
    }

    // 删除用户
    @Override
    public int deleteById(String id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updatePassword(String id, String password) {
        String s = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        User user = new User();
        user.setID(id);
        user.setPassword(s);
        return userMapper.updateById(user);
    }

}




