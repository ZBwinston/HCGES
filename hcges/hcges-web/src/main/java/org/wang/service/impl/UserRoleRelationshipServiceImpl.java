package org.wang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.UserRoleRelationship;
import org.wang.service.UserRoleRelationshipService;
import org.wang.mapper.UserRoleRelationshipMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author qwertyuiop
* @description 针对表【user_role_relationship】的数据库操作Service实现
* @createDate 2023-05-05 10:07:07
*/
@Service
public class UserRoleRelationshipServiceImpl extends ServiceImpl<UserRoleRelationshipMapper, UserRoleRelationship>
    implements UserRoleRelationshipService{

    @Resource
    private UserRoleRelationshipMapper userRoleRelationshipMapper;

    @Override
    public String getRoleIdById(String id) {

        return userRoleRelationshipMapper.getRoleIdById(id);
    }
}




