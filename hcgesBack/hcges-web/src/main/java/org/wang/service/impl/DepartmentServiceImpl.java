package org.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.Department;
import org.wang.model.DetailItem;
import org.wang.model.User;
import org.wang.service.DepartmentService;
import org.wang.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【department】的数据库操作Service实现
* @createDate 2023-05-16 10:51:46
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

    @Resource
    private DepartmentMapper departmentMapper;

    // 查询科室
    @Override
    public IPage<Department> selectDepartment(IPage<Department> page, QueryWrapper<Department> queryWrapper) {
        return departmentMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectList(null);
    }

    // 删除科室
    @Override
    public int deleteById(String id) {
        return departmentMapper.deleteById(id);
    }

    // 添加科室
    @Override
    public String addDepartment(Department department) {
        Department department1 = new Department();
        department1.setDepartment_name(department.getDepartment_name());
        department1.setManager(department.getManager());
        department1.setDepartment_introduction(department.getDepartment_introduction());
        boolean save = this.save(department1);
        if (save){
            return department1.getID();
        }

        return null;
    }

    // 修改信息
    @Override
    public int updateDepartment(Department department) {
        return departmentMapper.updateById(department);
    }

    // 查询名称
    @Override
    public String selectNameById(String id) {
        return departmentMapper.selectNameById(id);
    }
}




