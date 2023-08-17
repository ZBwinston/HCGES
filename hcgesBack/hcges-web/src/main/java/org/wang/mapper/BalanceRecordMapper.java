package org.wang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.wang.model.BalanceRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wang.model.vo.BalanceRecordVo;

import java.util.Date;
import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【balance_record】的数据库操作Mapper
* @createDate 2023-06-14 13:57:20
* @Entity org.wang.model.BalanceRecord
*/
public interface BalanceRecordMapper extends BaseMapper<BalanceRecord> {

    List<BalanceRecordVo> getBalanceRecord(IPage<BalanceRecordVo> iPage, @Param("minTime") Date minTime,@Param("maxTime") Date maxTime,@Param("uid") String uid);

}




