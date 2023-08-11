package org.wang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.wang.model.DetailItem;
import org.wang.model.ProjectInformation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author qwertyuiop
* @description 针对表【project_information】的数据库操作Mapper
* @createDate 2023-05-17 00:21:19
* @Entity org.wang.model.ProjectInformation
*/
public interface ProjectInformationMapper extends BaseMapper<ProjectInformation> {

    // 项目查询
    IPage<ProjectInformation> selectProject (Page<ProjectInformation> page, @Param("departmentId") String departmentId, @Param("projectName") String projectName);

}




