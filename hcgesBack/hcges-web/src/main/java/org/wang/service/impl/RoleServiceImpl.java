package org.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.Role;
import org.wang.service.RoleService;
import org.wang.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author qwertyuiop
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-05-05 10:06:00
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public IPage<Role> getRole(IPage<Role> iPage, QueryWrapper<Role> queryWrapper) {
        return roleMapper.selectPage(iPage,queryWrapper);
    }

    @Override
    public int deleteRole(String id) {
        return roleMapper.deleteById(id);
    }
}




