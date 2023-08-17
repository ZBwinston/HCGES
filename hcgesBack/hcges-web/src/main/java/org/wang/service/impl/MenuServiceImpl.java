package org.wang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.Menu;
import org.wang.service.MenuService;
import org.wang.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2023-05-05 09:34:50
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> searchMenu(String roleId) {
        return menuMapper.searchMenu(roleId);
    }
}




