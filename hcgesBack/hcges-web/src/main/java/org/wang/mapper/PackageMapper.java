package org.wang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.wang.model.Package;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author qwertyuiop
* @description 针对表【package】的数据库操作Mapper
* @createDate 2023-06-13 09:52:14
* @Entity org.wang.model.Package
*/
public interface PackageMapper extends BaseMapper<Package> {

    // 套餐查询
    IPage<Package> selectPackage(Page<Package> page, @Param("minPrice") Double minPrice,@Param("maxPrice") Double maxprice,@Param("packageName") String packageName);

}




