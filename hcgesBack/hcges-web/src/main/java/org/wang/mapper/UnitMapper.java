package org.wang.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wang.model.Unit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author qwertyuiop
* @description 针对表【unit】的数据库操作Mapper
* @createDate 2023-05-05 14:56:55
* @Entity org.wang.model.Unit
*/
public interface UnitMapper extends BaseMapper<Unit> {

    IPage<Unit> selectUnit(IPage<Unit> iPage, QueryWrapper<Unit> queryWrapper);
}




