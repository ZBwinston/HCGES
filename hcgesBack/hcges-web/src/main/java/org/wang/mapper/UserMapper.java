package org.wang.mapper;

import org.apache.ibatis.annotations.Select;
import org.wang.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author qwertyuiop
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-27 14:58:19
* @Entity org.wang.model.User
*/
public interface UserMapper extends BaseMapper<User> {

}




