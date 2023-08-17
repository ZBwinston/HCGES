package org.wang.service;

import org.wang.model.UserRoleRelationship;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author qwertyuiop
* @description 针对表【user_role_relationship】的数据库操作Service
* @createDate 2023-05-05 10:07:07
*/
public interface UserRoleRelationshipService extends IService<UserRoleRelationship> {

    /**
     *
     * @param id 用户ID
     * @return
     */
    String getRoleIdById(String id);

}
