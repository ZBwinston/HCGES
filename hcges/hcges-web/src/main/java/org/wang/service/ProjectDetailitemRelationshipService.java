package org.wang.service;

import org.wang.model.ProjectDetailitemRelationship;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
* @author qwertyuiop
* @description 针对表【project_detailitem_relationship】的数据库操作Service
* @createDate 2023-05-16 00:06:23
*/
public interface ProjectDetailitemRelationshipService extends IService<ProjectDetailitemRelationship> {

    /**
     *
     * @param map 细项ID
     * @return
     */
    int deleteShip(HashMap<String,Object> map);

}
