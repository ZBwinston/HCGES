package org.wang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.wang.model.BalanceRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wang.model.vo.BalanceRecordVo;

import java.util.Date;
import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【balance_record】的数据库操作Service
* @createDate 2023-06-14 13:57:20
*/
public interface BalanceRecordService extends IService<BalanceRecord> {

    /**
     * 新增账单
     * @param balanceRecord
     * @return
     */
    String addBalance(BalanceRecord balanceRecord);

    List<BalanceRecordVo> getBalanceRecord(IPage<BalanceRecordVo> iPage, Date minTime, Date maxTime, String uid);

}
