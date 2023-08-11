package org.wang.service;

import org.wang.model.DepartmentProject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
* @author qwertyuiop
* @description 针对表【department_project】的数据库操作Service
* @createDate 2023-05-17 00:13:27
*/
public interface DepartmentProjectService extends IService<DepartmentProject> {

    /**
     * 新增科室-项目
     * @param departmentProject
     * @return
     */
    String addDepartmentProject(DepartmentProject departmentProject);

    /**
     * 删除数据
     * @param map
     * @return
     */
    int deleteDepartmentProject(HashMap<String,Object> map);

}
