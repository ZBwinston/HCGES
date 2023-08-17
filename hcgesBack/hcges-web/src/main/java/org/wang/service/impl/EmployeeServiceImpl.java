package org.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.wang.model.Employee;
import org.wang.model.vo.EmployeeVo;
import org.wang.service.EmployeeService;
import org.wang.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Dictionary;
import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2023-06-08 16:23:37
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

    @Resource
    private EmployeeMapper employeeMapper;

    // 查询人员
    @Override
    public List<EmployeeVo> getEmployee(int page, int size, String keyword) {
        int start = (page - 1) * size;
        List<EmployeeVo> employeeVos = employeeMapper.getEmployee(start,size,keyword);
        return employeeVos;
    }

    // 删除人员
    @Override
    public int deleteEmployee(String id) {
        return employeeMapper.deleteById(id);
    }

    @Override
    public String addEmployee(Employee employee) {
        Employee employee1 = new Employee();
        employee1.setID(employee.getID());
        employee1.setName(employee.getName());
        employee1.setGender(employee.getGender());
        employee1.setAge(employee.getAge());
        employee1.setPhone(employee.getPhone());
        employee1.setID_card_number(employee.getID_card_number());
        employee1.setBilling_status("0");
        employee1.setUnit_ID(employee.getUnit_ID());


        boolean save = this.save(employee1);
        if (save){
            return employee1.getID();
        }

        return null;
    }

    @Override
    public String saveEmployee(List<Employee> list) {
        employeeMapper.saveBatch(list);
        return null;
    }

//    @Override
//    public void saveBatch(List<EmployeeVo> list) {
//        employeeMapper.saveBatch(list);
//    }

    @Override
    public int countEmployee(QueryWrapper<EmployeeVo> queryWrapper) {
        return employeeMapper.countEmployee(queryWrapper);
    }

    @Override
    public String getUidByEid(String id) {
        return employeeMapper.getUidByEid(id);
    }
}




