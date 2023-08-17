package org.wang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.wang.model.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wang.model.vo.EmployeeVo;

import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【employee】的数据库操作Service
* @createDate 2023-06-08 16:23:37
*/
public interface EmployeeService extends IService<Employee> {

    /**
     * 查询人员
     * @param page
     * @param size
     * @param keyword
     * @return
     */
    List<EmployeeVo> getEmployee(int page, int size, String keyword);

    /**
     * 删除人员
     * @param id
     * @return
     */
    int deleteEmployee(String id);

    /**
     * 新增人员
     * @param employee
     * @return
     */
    String addEmployee(Employee employee);

    String saveEmployee(List<Employee> list);

    /**
     * 保存用户
     * @param list
     */
//    void saveBatch(List<EmployeeVo> list);

    /**
     * 查询数据量
     * @param queryWrapper
     * @return
     */
    int countEmployee(QueryWrapper<EmployeeVo> queryWrapper);

    /**
     * 根据员工ID查找公司ID
     * @param id
     * @return
     */
    String getUidByEid(String id);

}
