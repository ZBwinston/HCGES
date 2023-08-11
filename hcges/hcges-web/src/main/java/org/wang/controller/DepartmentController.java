package org.wang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wang.model.Department;
import org.wang.service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 科室查询
    @GetMapping("/selectDepartment")
    private IPage<Department> selectDepartment( @RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer pageSize) {

        IPage<Department> iPage = new Page<>(page,pageSize);

        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("department_name",keyword);

        return departmentService.selectDepartment(iPage,queryWrapper);
    }

    // 所有科室信息查询
    @GetMapping("/selectDepartmentAll")
    private List<Department> selectDepartmentAll(){
        return departmentService.selectAll();
    }

    // 新增科室
    @PostMapping("/addDepartment")
    private String addDepartment(@RequestBody Department department){

        String id = departmentService.addDepartment(department);

        return id;
    }

    // 删除科室
    @GetMapping("/deleteDepartment")
    private void deleteDepartment(@RequestParam String id){
        departmentService.deleteById(id);
    }

    // 修改科室
    @PostMapping("/updateDepartment")
    private void updateDepartment(@RequestBody Department department){
        departmentService.updateDepartment(department);
    }

    // 根据科室ID查找名字
    @GetMapping("/selectNameById")
    private String selectNameById(@RequestParam String id){
        return departmentService.selectNameById(id);
    }
}
