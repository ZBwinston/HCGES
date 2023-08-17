package org.wang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wang.model.DetailItem;
import org.wang.service.DetailItemService;

@RestController
public class DetailController {

    @Autowired
    private DetailItemService detailItemService;

    // 项目细项查询
    @GetMapping("/selectDetail")
    private IPage<DetailItem> selectDetail(@RequestParam String project,@RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer pageSize) {

        IPage<DetailItem> detailItemPage = detailItemService.selectDetail(new Page<>(page, pageSize), project, keyword);

        return detailItemPage;
    }

    // 新增项目细项
    @PostMapping("/addDetail")
    private String addDetail(@RequestBody DetailItem detailItem){
        System.out.println(detailItem);
        String id = detailItemService.addDetail(detailItem);
        return id;
    }

    // 删除项目细项
    @GetMapping("/deleteDetail")
    private void deleteDetail(@RequestParam String id){
        detailItemService.deleteById(id);
    }

    // 修改项目细项
    @PostMapping("/updateDetail")
    private void updateDetail(@RequestBody DetailItem detailItem){
        detailItemService.updateDetail(detailItem);
    }
}
