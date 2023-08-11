package org.wang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.model.Role;
import org.wang.service.RoleService;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 查询角色
    @GetMapping("/getRole")
    private IPage<Role> getRole(@RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer pageSize){
        IPage<Role> iPage = new Page<>(page,pageSize);

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("Role_name",keyword);

        return roleService.getRole(iPage,queryWrapper);
    }

    // 删除角色
    @GetMapping("deleteRole")
    private void deleteRole(@RequestParam("rid") String rid){
        roleService.deleteRole(rid);
    }
}
