package org.wang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wang.model.Unit;

/**
* @author qwertyuiop
* @description 针对表【unit】的数据库操作Service
* @createDate 2023-05-05 14:56:55
*/
public interface UnitService extends IService<Unit> {

    /**
     * 新增单位账号
     * @param unit 单位对象
     * @return
     */
    String unitRegister(Unit unit);

    /**
     * 删除单位信息
     * @param id
     * @return
     */
    int deleteById(String id);

    IPage<Unit> selectUnit(IPage<Unit> iPage, QueryWrapper<Unit> queryWrapper);

    /**
     * 新增单位
     * @param unit
     * @return
     */
    String addUnit(Unit unit);

    /**
     * 充值
     * @param id
     * @param accountBalance
     * @return
     */
    int updateById(String id,Double accountBalance);

    Unit selectById(String id);

}
