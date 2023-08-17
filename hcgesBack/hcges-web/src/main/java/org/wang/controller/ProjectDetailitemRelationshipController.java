package org.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.service.ProjectDetailitemRelationshipService;

import java.util.HashMap;

@RestController
public class ProjectDetailitemRelationshipController {

    @Autowired
    private ProjectDetailitemRelationshipService projectDetailitemRelationshipService;

    @GetMapping("/deleteShip")
    private void deleteShip(@RequestParam String id){
        HashMap<String,Object> map = new HashMap<>();
        map.put("Detail_item_ID",id);
        projectDetailitemRelationshipService.deleteShip(map);
    }
}
