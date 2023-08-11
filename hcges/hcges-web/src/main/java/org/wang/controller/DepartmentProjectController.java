package org.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.model.DepartmentProject;
import org.wang.service.DepartmentProjectService;

import java.util.HashMap;

@RestController
public class DepartmentProjectController {

    @Autowired
    private DepartmentProjectService departmentProjectService;

    // 新增科室-项目
    @GetMapping("/addDepartmentProject")
    private void addDepartmentProject(@RequestParam String departmentId,@RequestParam String projectId){
        DepartmentProject departmentProject = new DepartmentProject();
        departmentProject.setDepartment_ID(departmentId);
        departmentProject.setProject_ID(projectId);

        departmentProjectService.addDepartmentProject(departmentProject);
    }

    // 删除科室-项目
    @GetMapping("/deleteDepartmentProject")
    private void deleteDepartmentProject(@RequestParam String id){
        HashMap<String,Object> map = new HashMap<>();
        map.put("Project_ID",id);
        departmentProjectService.deleteDepartmentProject(map);
    }
}
