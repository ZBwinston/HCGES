package org.wang.mapper;

import org.apache.ibatis.annotations.Select;
import org.wang.model.UserRoleRelationship;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author qwertyuiop
* @description 针对表【user_role_relationship】的数据库操作Mapper
* @createDate 2023-05-05 10:07:07
* @Entity org.wang.model.UserRoleRelationship
*/
public interface UserRoleRelationshipMapper extends BaseMapper<UserRoleRelationship> {

    @Select("select Role_ID from user_role_relationship where User_ID = #{id}")
    String getRoleIdById(String id);

}




