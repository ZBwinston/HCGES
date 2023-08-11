package org.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.service.UserRoleRelationshipService;

@RestController
public class UserRoleRelationshipController {

    @Autowired
    private UserRoleRelationshipService userRoleRelationshipService;

    // 根据用户id查询角色id
    @GetMapping("/getRoleIdById")
    private String getRoleIdById(@RequestParam String userId){
        return userRoleRelationshipService.getRoleIdById(userId);
    }
}
