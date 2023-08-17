package org.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wang.model.Unit;
import org.wang.service.UnitService;
import org.wang.mapper.UnitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author qwertyuiop
* @description 针对表【unit】的数据库操作Service实现
* @createDate 2023-05-05 14:56:55
*/
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit>
    implements UnitService{

    @Resource
    private UnitMapper unitMapper;

    // 注册单位账号
    @Override
    public String unitRegister(Unit unit) {
        Unit unit1 = new Unit();
        unit1.setUnit_name(unit.getUnit_name());
        unit1.setContact_person(unit.getContact_person());
        unit1.setPhone(unit.getPhone());
        unit1.setAccount_balance(0.00);
        boolean save = this.save(unit1);
        if (save){
            return unit1.getID();
        }
        return null;
    }

    // 删除单位账号
    @Override
    public int deleteById(String id) {
        return unitMapper.deleteById(id);
    }

    @Override
    public IPage<Unit> selectUnit(IPage<Unit> iPage, QueryWrapper<Unit> queryWrapper) {
//        return unitMapper.selectUnit(iPage,queryWrapper);
        return unitMapper.selectPage(iPage,queryWrapper);
    }

    @Override
    public String addUnit(Unit unit) {
        Unit unit1 = new Unit();
        unit1.setUnit_name(unit.getUnit_name());
        unit1.setContact_person(unit.getContact_person());
        unit1.setPhone(unit.getPhone());
        unit1.setAccount_balance(0.00);
        boolean save = this.save(unit1);
        if (save){
            return unit1.getID();
        }

        return null;
    }

    // 充值
    @Override
    public int updateById(String id, Double accountBalance) {
        Unit unit = new Unit();
        unit.setID(id);
        unit.setAccount_balance(accountBalance);

        return unitMapper.updateById(unit);
    }

    @Override
    public Unit selectById(String id) {
        return unitMapper.selectById(id);
    }


}




