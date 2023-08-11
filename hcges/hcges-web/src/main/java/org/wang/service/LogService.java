package org.wang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wang.model.Log;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author qwertyuiop
* @description 针对表【log】的数据库操作Service
* @createDate 2023-06-15 10:34:56
*/
public interface LogService extends IService<Log> {

    /**
     * 记录日志
     * @param log
     * @return
     */
    String addLog(Log log);

    /**
     * 查询日志
     * @param page
     * @return
     */
    List<Log> listLogs(Page<Log> page);

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLog(String id);

}
