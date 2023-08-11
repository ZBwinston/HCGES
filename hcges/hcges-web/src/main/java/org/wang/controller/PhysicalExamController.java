package org.wang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.model.vo.PhysicalExamVo;
import org.wang.service.PhysicalExamService;

import java.util.List;

@RestController
public class PhysicalExamController {

    @Autowired
    private PhysicalExamService physicalExamService;

    // 查询体检单
    @GetMapping("/getPhysicalExamList")
    private IPage<PhysicalExamVo> getPhysicalExamList(@RequestParam(defaultValue = "") String unitName,
                                                      @RequestParam(defaultValue = "") String employeeName,
                                                      @RequestParam Integer page,
                                                      @RequestParam Integer pageSize,
                                                      @RequestParam String state) {
        System.out.println("lllllll" + state);
        Page<PhysicalExamVo> page1 = new Page<>(page,pageSize);
        return physicalExamService.getPhysicalExamList(page1,unitName,employeeName,state);
    }
}
