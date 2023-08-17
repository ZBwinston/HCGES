package org.wang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wang.model.DetailItem;
import org.wang.model.ProjectInformation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author qwertyuiop
* @description 针对表【project_information】的数据库操作Service
* @createDate 2023-05-17 00:21:19
*/
public interface ProjectInformationService extends IService<ProjectInformation> {

    /**
     *
     * @param page 分页查询
     * @param departmentId
     * @param projectName
     * @return
     */
    IPage<ProjectInformation> selectProject (Page<ProjectInformation> page, String departmentId, String projectName);

    /**
     * 新增项目
     * @param projectInformation
     * @return
     */
    String addProject(ProjectInformation projectInformation);

    /**
     * 删除项目
     * @param id
     * @return
     */
    int deleteById(String id);

}
