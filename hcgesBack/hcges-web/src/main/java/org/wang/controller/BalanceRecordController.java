package org.wang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.model.vo.BalanceRecordVo;
import org.wang.service.BalanceRecordService;

import java.util.Date;
import java.util.List;

@RestController
public class BalanceRecordController {

    @Autowired
    private BalanceRecordService balanceRecordService;

    // 查询账单
//    @GetMapping("/getBalanceRecord")
//    private List<BalanceRecordVo> getBalanceRecord(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize,@RequestParam("minTime") Date minTime,@RequestParam("maxTime") Date maxTime,@RequestParam("uid") String uid){
//        IPage<BalanceRecordVo> iPage = new Page<>(page,pageSize);
//
//        return balanceRecordService.getBalanceRecord(iPage,minTime,maxTime,uid);
//    }

//    @GetMapping("/getBalanceRecord")
//    private List<BalanceRecordVo> getBalanceRecord(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("minTime") long minTime, @RequestParam("maxTime") long maxTime, @RequestParam("uid") String uid) {
//        IPage<BalanceRecordVo> iPage = new Page<>(page, pageSize);
//        Date minDate = new Date(minTime);
//        Date maxDate = new Date(maxTime);
//        return balanceRecordService.getBalanceRecord(iPage, minDate, maxDate, uid);
//    }

    @GetMapping("/getBalanceRecord")
    private List<BalanceRecordVo> getBalanceRecord(@RequestParam("page") int page,
                                                   @RequestParam("pageSize") int pageSize,
                                                   @RequestParam(value = "minTime", required = false) Long minTime,
                                                   @RequestParam(value = "maxTime", required = false) Long maxTime,
                                                   @RequestParam("uid") String uid) {
        IPage<BalanceRecordVo> iPage = new Page<>(page, pageSize);
        Date minDate = null;
        Date maxDate = null;
        if (minTime != null && maxTime != null) {
            minDate = new Date(minTime);
            maxDate = new Date(maxTime);
        }
        return balanceRecordService.getBalanceRecord(iPage, minDate, maxDate, uid);
    }

//    @GetMapping("/getBalanceRecord")
//    private List<BalanceRecordVo> getBalanceRecord(@RequestParam("page") int page,
//                                                   @RequestParam("pageSize") int pageSize,
//                                                   @RequestParam(value = "minTime", required = false) Long minTime,
//                                                   @RequestParam(value = "maxTime", required = false) Long maxTime,
//                                                   @RequestParam("uid") String uid) {
//        IPage<BalanceRecordVo> iPage = new Page<>(page, pageSize);
//        Date minDate = null;
//        Date maxDate = null;
//        if (minTime != null && maxTime != null) {
//            minDate = new Date(minTime / 1000); // 秒级时间戳转为毫秒级时间戳
//            maxDate = new Date(maxTime / 1000);
//        }
//        return balanceRecordService.getBalanceRecord(iPage, minDate, maxDate, uid);
//    }

}
