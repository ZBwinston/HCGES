package org.wang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wang.model.DetailItem;
import org.wang.service.DetailItemService;
import org.wang.mapper.DetailItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author qwertyuiop
* @description 针对表【detail_item】的数据库操作Service实现
* @createDate 2023-05-15 13:57:14
*/
@Service
public class DetailItemServiceImpl extends ServiceImpl<DetailItemMapper, DetailItem>
    implements DetailItemService{

    @Resource
    private DetailItemMapper detailItemMapper;

    @Override
    public IPage<DetailItem> selectDetail(Page<DetailItem> page, String projectId, String detailName) {
        return detailItemMapper.selectDetail(page,projectId,detailName);
    }

    // 删除细项
    @Override
    public int deleteById(String id) {
        return detailItemMapper.deleteById(id);
    }

    // 新增细项
    @Override
    public String addDetail(DetailItem detailItem) {
        DetailItem detailItem1 = new DetailItem();
        detailItem1.setID(detailItem.getID());
        detailItem1.setDetail_item_name(detailItem.getDetail_item_name());
        detailItem1.setMeasurement_unit(detailItem.getMeasurement_unit());
        detailItem1.setUpper_limit_value(detailItem.getUpper_limit_value());
        detailItem1.setLower_limit_value(detailItem.getLower_limit_value());
        boolean save = this.save(detailItem1);
        if(save){
            return detailItem1.getID();
        }

        return null;
    }

    // 修改细项
    @Override
    public int updateDetail(DetailItem detailItem) {
        return detailItemMapper.updateById(detailItem);
    }

}




