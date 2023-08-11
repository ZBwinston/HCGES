package org.wang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wang.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author qwertyuiop
* @description 针对表【role】的数据库操作Service
* @createDate 2023-05-05 10:06:00
*/
public interface RoleService extends IService<Role> {

    /**
     * 查询用户
     * @param iPage
     * @param queryWrapper
     * @return
     */
    IPage<Role> getRole(IPage<Role> iPage, QueryWrapper<Role> queryWrapper);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int deleteRole(String id);

}
