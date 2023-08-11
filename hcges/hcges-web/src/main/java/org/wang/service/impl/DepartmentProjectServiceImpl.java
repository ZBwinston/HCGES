package org.wang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.DepartmentProject;
import org.wang.service.DepartmentProjectService;
import org.wang.mapper.DepartmentProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
* @author qwertyuiop
* @description 针对表【department_project】的数据库操作Service实现
* @createDate 2023-05-17 00:13:27
*/
@Service
public class DepartmentProjectServiceImpl extends ServiceImpl<DepartmentProjectMapper, DepartmentProject>
    implements DepartmentProjectService{

    @Resource
    private DepartmentProjectMapper departmentProjectMapper;

    @Override
    public String addDepartmentProject(DepartmentProject departmentProject) {
        DepartmentProject departmentProject1 = new DepartmentProject();
        departmentProject1.setDepartment_ID(departmentProject.getDepartment_ID());
        departmentProject1.setProject_ID(departmentProject.getProject_ID());
        boolean save = this.save(departmentProject1);
        if(save){
            return departmentProject1.getID();
        }

        return null;
    }

    @Override
    public int deleteDepartmentProject(HashMap<String, Object> map) {
        return departmentProjectMapper.deleteByMap(map);
    }
}




