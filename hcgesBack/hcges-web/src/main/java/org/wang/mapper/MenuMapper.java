package org.wang.mapper;

import org.apache.ibatis.annotations.Select;
import org.wang.model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2023-05-05 09:34:50
* @Entity org.wang.model.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT * FROM menu WHERE ID IN (SELECT Menu_PID FROM role_menu WHERE role_id=#{roleId})")
    List<Menu> searchMenu(String roleId);

}




