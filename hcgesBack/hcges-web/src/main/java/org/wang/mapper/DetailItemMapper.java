package org.wang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.wang.model.DetailItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author qwertyuiop
* @description 针对表【detail_item】的数据库操作Mapper
* @createDate 2023-05-15 13:57:14
* @Entity org.wang.model.DetailItem
*/
public interface DetailItemMapper extends BaseMapper<DetailItem> {

    // 细项查询
    IPage<DetailItem> selectDetail(Page<DetailItem> page, @Param("projectId") String projectId, @Param("detailName") String detailName);

}




