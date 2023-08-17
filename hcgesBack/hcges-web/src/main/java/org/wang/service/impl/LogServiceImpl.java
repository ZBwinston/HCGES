package org.wang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.Log;
import org.wang.service.LogService;
import org.wang.mapper.LogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【log】的数据库操作Service实现
* @createDate 2023-06-15 10:34:56
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService{

    @Resource
    private LogMapper logMapper;

    @Override
    public String addLog(Log log) {
        Log log1 = new Log();
        log1.setID(log.getID());
        log1.setAction_time(log.getAction_time());
        log1.setOperator(log.getOperator());
        log1.setAction_type(log.getAction_type());
        log1.setAction_content(log.getAction_content());

        this.save(log1);

        return null;
    }

    @Override
    public List<Log> listLogs(Page<Log> page) {
        logMapper.selectPage(page, null);
        return page.getRecords();
    }

    @Override
    public int deleteLog(String id) {
        return logMapper.deleteById(id);
    }
}




