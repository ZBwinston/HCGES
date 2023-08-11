package org.wang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wang.model.ProjectDetailitemRelationship;
import org.wang.service.ProjectDetailitemRelationshipService;
import org.wang.mapper.ProjectDetailitemRelationshipMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
* @author qwertyuiop
* @description 针对表【project_detailitem_relationship】的数据库操作Service实现
* @createDate 2023-05-16 00:06:23
*/
@Service
public class ProjectDetailitemRelationshipServiceImpl extends ServiceImpl<ProjectDetailitemRelationshipMapper, ProjectDetailitemRelationship>
    implements ProjectDetailitemRelationshipService{

    @Resource
    private ProjectDetailitemRelationshipMapper projectDetailitemRelationshipMapper;

    @Override
    public int deleteShip(HashMap<String,Object> map) {
        return projectDetailitemRelationshipMapper.deleteByMap(map);
    }
}




