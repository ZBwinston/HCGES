package org.wang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.ProjectInformation;
import org.wang.service.ProjectInformationService;
import org.wang.mapper.ProjectInformationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author qwertyuiop
* @description 针对表【project_information】的数据库操作Service实现
* @createDate 2023-05-17 00:21:19
*/
@Service
public class ProjectInformationServiceImpl extends ServiceImpl<ProjectInformationMapper, ProjectInformation>
    implements ProjectInformationService{

    @Resource
    private ProjectInformationMapper projectInformationMapper;

    @Override
    public IPage<ProjectInformation> selectProject(Page<ProjectInformation> page, String departmentId, String projectName) {
        return projectInformationMapper.selectProject(page, departmentId, projectName);
    }

    // 新增项目
    @Override
    public String addProject(ProjectInformation projectInformation) {
        ProjectInformation projectInformation1 = new ProjectInformation();
        projectInformation1.setProject_name(projectInformation.getProject_name());
        projectInformation1.setDepartment_name(projectInformation.getDepartment_name());
        projectInformation1.setPrice(projectInformation.getPrice());
        boolean save = this.save(projectInformation1);
        if (save){
            return projectInformation1.getID();
        }

        return null;
    }

    @Override
    public int deleteById(String id) {
        return projectInformationMapper.deleteById(id);
    }
}




