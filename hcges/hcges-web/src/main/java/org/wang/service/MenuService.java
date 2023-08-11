package org.wang.service;

import org.wang.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【menu】的数据库操作Service
* @createDate 2023-05-05 09:34:50
*/
public interface MenuService extends IService<Menu> {

    List<Menu> searchMenu(String roleId);

}
