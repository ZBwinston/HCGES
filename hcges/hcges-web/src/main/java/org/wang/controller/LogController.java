package org.wang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wang.model.Log;
import org.wang.service.LogService;

import java.util.List;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    // 查询操作日志
    @GetMapping("/listLogs")
    private ResponseEntity<List<Log>> listLogs(@RequestParam("page") int page,@RequestParam("pageSize") int pageSize){
        Page<Log> page1 = new Page<>(page,pageSize);
        List<Log> logs = logService.listLogs(page1);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/deleteLog")
    private ResponseEntity<String> deleteLog(@RequestParam("id") String id){
        logService.deleteLog(id);
        return ResponseEntity.ok("删除成功");
    }
}
