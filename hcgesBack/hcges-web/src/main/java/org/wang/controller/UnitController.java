package org.wang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wang.model.BalanceRecord;
import org.wang.model.Unit;
import org.wang.service.BalanceRecordService;
import org.wang.service.UnitService;

import java.util.Date;
import java.util.Map;

@RestController
public class UnitController {

    @Autowired
    private UnitService unitService;

    @Autowired
    private BalanceRecordService balanceRecordService;

    // 创建单位账号
    @PostMapping("/unitRegister")
    private String unitRegister(@RequestBody Unit unit){
        String id = unitService.unitRegister(unit);

        return id;
    }

    // 查询单位列表
    @GetMapping("/queryUnit")
    private IPage<Unit> queryUnit(@RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer pageSize){
        IPage<Unit> iPage = new Page<>(page,pageSize);

        QueryWrapper<Unit> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("unit_name",keyword);
        return unitService.selectUnit(iPage,queryWrapper);
    }

    // 新增单位
    @PostMapping("/addUnit")
    private String addUnit(@RequestBody Unit unit){
        return unitService.addUnit(unit);
    }

    // 删除单位
    @PostMapping("/deleteById")
    private String deleteById(@RequestBody Map<String, Object> json){
        String id = json.get("id").toString();
        unitService.deleteById(id);
        return id;
    }

    // 充值
    @PostMapping("/pay")
    private void pay(@RequestBody Unit unit){
        System.out.println("看看传进来什么东西："+unit.getID());
        Unit resUnit = unitService.selectById(unit.getID());
        System.out.println("看看数据库什么东西："+resUnit.getAccount_balance());
        Double nowNum = unit.getAccount_balance()+resUnit.getAccount_balance();
        unitService.updateById(unit.getID(),nowNum);

        BalanceRecord balanceRecord = new BalanceRecord();
        balanceRecord.setUnit_ID(unit.getID());
        balanceRecord.setAction_type("收入");
        balanceRecord.setAmount(unit.getAccount_balance());
        balanceRecord.setAction_time(new Date());

        balanceRecordService.addBalance(balanceRecord);
    }
}
