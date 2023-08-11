package org.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.model.Menu;
import org.wang.service.MenuService;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/searchMenu")
    private List<Menu> searchMenu(@RequestParam String roleId){
       return menuService.searchMenu(roleId);
    }
}
