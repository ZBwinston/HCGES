package org.wang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.model.ProjectInformation;
import org.wang.service.ProjectInformationService;

@RestController
public class ProjectInformationController {

    @Autowired
    private ProjectInformationService projectInformationService;

    // 项目查询
    @GetMapping("/selectProject")
    private IPage<ProjectInformation> selectProject(@RequestParam String department, @RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer pageSize) {

        IPage<ProjectInformation> projectInformationIPage = projectInformationService.selectProject(new Page<>(page,pageSize),department,keyword);

        return projectInformationIPage;
    }

    // 新增项目
    @GetMapping("/addProject")
    private String addProject(@RequestParam String projectName,@RequestParam String departmentName,@RequestParam String price){
        ProjectInformation projectInformation = new ProjectInformation();
        projectInformation.setProject_name(projectName);
        projectInformation.setDepartment_name(departmentName);
        projectInformation.setPrice(Double.parseDouble(price));

        String id = projectInformationService.addProject(projectInformation);

        return id;
    }

    // 删除项目
    @GetMapping("/deleteProject")
    private void deleteProject(@RequestParam String id){
        projectInformationService.deleteById(id);
    }
}
