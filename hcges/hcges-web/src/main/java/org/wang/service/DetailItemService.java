package org.wang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wang.model.DetailItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author qwertyuiop
* @description 针对表【detail_item】的数据库操作Service
* @createDate 2023-05-15 13:57:14
*/
public interface DetailItemService extends IService<DetailItem> {

    /**
     *
     * @param page 当前页和数据量
     * @param projectId 项目ID
     * @param detailName 查询文本框内容
     * @return
     */
    IPage<DetailItem> selectDetail(Page<DetailItem> page, String projectId, String detailName);

    /**
     *
     * @param id 细项id
     * @return
     */
    int deleteById(String id);

    /**
     *
     * @param detailItem 细项对象
     * @return
     */
    String addDetail(DetailItem detailItem);

    /**
     *  修改细项
     * @param detailItem 细项对象
     * @return
     */
    int updateDetail(DetailItem detailItem);

}
