package org.wang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.model.Package;
import org.wang.service.PackageService;

@RestController
public class PackageController {

    @Autowired
    private PackageService packageService;

    // 查询套餐
    @GetMapping("/selectPackage")
    private IPage<Package> selectPackage(@RequestParam(required = false, defaultValue = "-1") Double minPrice,@RequestParam(required = false, defaultValue = "-1") Double maxPrice,@RequestParam String packageName,@RequestParam Integer page, @RequestParam Integer pageSize){

        if (minPrice == -1) {
            minPrice = null;
        }
        if (maxPrice == -1) {
            maxPrice = null;
        }

        return packageService.selectPackage(new Page<>(page,pageSize),minPrice,maxPrice,packageName);
    }

    // 新增套餐
    @GetMapping("/addPackage")
    private void addPackage(@RequestParam String packageName){
        packageService.addPackage(packageName);
    }

    // 删除套餐
    @GetMapping("/deletePackage")
    private void deletePackage(@RequestParam String id){
        packageService.deletePackage(id);
    }
}
