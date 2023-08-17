package org.wang.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wang.listener.EmployeeDataListener;
import org.wang.model.BalanceRecord;
import org.wang.model.Employee;
import org.wang.model.PhysicalExam;
import org.wang.model.Unit;
import org.wang.model.vo.EmployeeVo;
import org.wang.service.BalanceRecordService;
import org.wang.service.EmployeeService;
import org.wang.service.PhysicalExamService;
import org.wang.service.UnitService;
import org.wang.utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private PhysicalExamService physicalExamService;

    @Autowired
    private BalanceRecordService balanceRecordService;

    // 查询人员
    @GetMapping("/getEmployee")
    private List<EmployeeVo> getEmployee(@RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer pageSize){

        return employeeService.getEmployee(page,pageSize,keyword);

    }

    // 删除人员
    @GetMapping("/deleteEmployee")
    private void deleteEmployee(@RequestParam String id){
        employeeService.deleteEmployee(id);
    }

    // 新增人员
    @PostMapping("/addEmployee")
    private void addEmployee(@RequestBody EmployeeVo employeeVo){
        Employee employee = new Employee();
        employee.setName(employeeVo.getName());
        employee.setGender(employeeVo.getGender());
        employee.setAge(employeeVo.getAge());
        employee.setPhone(employeeVo.getPhone());
        employee.setID_card_number(employeeVo.getID_card_number());
        employee.setUnit_ID(employeeVo.getUnit_name());

        employeeService.addEmployee(employee);
    }

    // 下载模版
    @GetMapping("/exportWeb")
    public void export2Web(HttpServletResponse response) {
        try {
            ExcelUtils.export2Web(response, "用户表", "用户信息", Employee.class, employeeService.getEmployee(1,0,""));
        } catch (Exception e) {
        }
    }

    // 下载员工信息
    @GetMapping("/exportAll")
    public void exportAll(HttpServletResponse response,@RequestParam("key") String keyword) {

        QueryWrapper<EmployeeVo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.lambda().like(EmployeeVo::getUnit_name, keyword);
        }

        int count = employeeService.countEmployee(queryWrapper);

        try {
            ExcelUtils.export2Web(response, "用户表", "用户信息", Employee.class, employeeService.getEmployee(1,count,keyword));
        } catch (Exception e) {
        }
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        System.out.println("_________________________");
        System.out.println(file);
        EasyExcel.read(file.getInputStream(), Employee.class, new EmployeeDataListener(employeeService)).sheet().doRead();
        return "上传成功";
    }

    // 查询员工是否开单
    @GetMapping("/isBilling")
    private ResponseEntity<String> isBilling(@RequestParam("id") String id){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID",id);
        queryWrapper.eq("Billing_status","0");

        Long i = employeeService.count(queryWrapper);

        if (i == 1){
            return ResponseEntity.ok("该用户未开单");
        }

        return ResponseEntity.badRequest().body("该用户已开单");

    }

    // 员工开单
    @GetMapping("/toBilling")
    private ResponseEntity<String> toBilling(@RequestParam("id") String id,@RequestParam("price") Double price,@RequestParam("userId") String userId,@RequestParam("packageId") String packageId){
        // 根据员工id查询公司id
        String uidByEid = employeeService.getUidByEid(id);

        QueryWrapper<Unit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID",uidByEid);
        queryWrapper.ge("Account_balance",price);

        long count = unitService.count(queryWrapper);

        if (count == 0){
            return ResponseEntity.badRequest().body("该账户余额不足");
        }

        // 扣掉账户里的余额
        Unit unit = unitService.selectById(uidByEid);
        Double money = unit.getAccount_balance()-price;

        unitService.updateById(uidByEid,money);

        // 生成账单
        BalanceRecord balanceRecord = new BalanceRecord();
        balanceRecord.setUnit_ID(uidByEid);
        balanceRecord.setAction_type("支出");
        balanceRecord.setAmount(price);
        balanceRecord.setAction_time(new Date());

        balanceRecordService.addBalance(balanceRecord);

        // 新增体检单
        PhysicalExam physicalExam = new PhysicalExam();
        physicalExam.setEmployee_ID(userId);
        physicalExam.setPackage_ID(packageId);
        physicalExamService.addPhysicalExam(physicalExam);

        return ResponseEntity.ok("开单成功");
    }

}
