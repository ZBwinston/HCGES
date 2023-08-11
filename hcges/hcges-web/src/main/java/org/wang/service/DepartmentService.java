package org.wang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wang.model.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wang.model.DetailItem;
import org.wang.model.User;

import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【department】的数据库操作Service
* @createDate 2023-05-16 10:51:46
*/
public interface DepartmentService extends IService<Department> {

    /**
     *
     * @param page 当前页和数据量
     * @return
     */
    IPage<Department> selectDepartment(IPage<Department> page, QueryWrapper<Department> queryWrapper);

    /**
     *  查询所有科室信息
     * @return
     */
    List<Department> selectAll();

    /**
     *
     * @param id 细项id
     * @return
     */
    int deleteById(String id);

    /**
     *
     * @param department 科室对象
     * @return
     */
    String addDepartment(Department department);

    /**
     *  修改细项
     * @param department 科室对象
     * @return
     */
    int updateDepartment(Department department);

    /**
     *  根据科室id查名字
     * @param id 科室id
     * @return
     */
    String selectNameById(String id);


}
