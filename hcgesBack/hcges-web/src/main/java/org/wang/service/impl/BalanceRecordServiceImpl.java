package org.wang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.BalanceRecord;
import org.wang.model.vo.BalanceRecordVo;
import org.wang.service.BalanceRecordService;
import org.wang.mapper.BalanceRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【balance_record】的数据库操作Service实现
* @createDate 2023-06-14 13:57:20
*/
@Service
public class BalanceRecordServiceImpl extends ServiceImpl<BalanceRecordMapper, BalanceRecord>
    implements BalanceRecordService{

    @Resource
    private BalanceRecordMapper balanceRecordMapper;

    // 新增账单
    @Override
    public String addBalance(BalanceRecord balanceRecord) {
        BalanceRecord balanceRecord1 = new BalanceRecord();
        balanceRecord1.setID(balanceRecord.getID());
        balanceRecord1.setUnit_ID(balanceRecord.getUnit_ID());
        balanceRecord1.setAction_type(balanceRecord.getAction_type());
        balanceRecord1.setAmount(balanceRecord.getAmount());
        balanceRecord1.setAction_time(balanceRecord.getAction_time());

        boolean save = this.save(balanceRecord1);

        if (save){
            return balanceRecord1.getID();
        }

        return null;
    }

    @Override
    public List<BalanceRecordVo> getBalanceRecord(IPage<BalanceRecordVo> iPage, Date minTime, Date maxTime, String uid) {
        return balanceRecordMapper.getBalanceRecord(iPage,minTime,maxTime,uid);
    }
}




