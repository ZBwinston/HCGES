package org.wang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wang.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author qwertyuiop
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-27 14:58:19
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 用户密码
     * @param real_name 真实姓名
     * @param department 所属科室
     * @return 新用户ID
     */
    String userRegister(String username,String password,String real_name,String department);

    /**
     *  用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return 脱敏后的用户信息
     */
    User doLogin(String username, String password, HttpServletRequest request);

    /**
     * 修改信息
     * @param id ID
     * @param username 用户名
     * @param password 密码
     * @param real_name 真实姓名
     * @param department 科室
     * @return
     */
    int updateById(String id,String username,String password,String real_name,String department);

    /**
     *  查询用户
     * @param iPage
     * @param queryWrapper
     * @return
     */
    IPage<User> getUser(IPage<User> iPage, QueryWrapper<User> queryWrapper);

    // 查询数据数量
    Long selectCount(QueryWrapper<User> queryWrapper);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteById(String id);

    int updatePassword(String id,String password);
}
