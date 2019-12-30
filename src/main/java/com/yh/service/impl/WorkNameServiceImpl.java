package com.yh.service.impl;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.yh.annotation.PlatHandler;
import com.yh.bean.StudentBean;
import com.yh.bean.WorkName;
import com.yh.dao.WorkNameMapper;
import com.yh.dataset.DynamicDataSourceContextHolder;
import com.yh.service.AbstractAccountHandler;
import com.yh.service.WorkNameService;
import com.yh.util.SpringContextHolder;
import com.yh.util.SpringUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: yinhui
 * @Date: 2019/4/24 13:52
 * @Version 1.0
 */
@Service
@PlatHandler(value = "Alipay")
public class WorkNameServiceImpl extends AbstractAccountHandler implements WorkNameService {

    @Autowired
    private WorkNameMapper workNameMapper;

    @Override
    @Cached(name = "WorkName.getById" , key = "#id",expire = 3600 ,cacheType = CacheType.BOTH)
    @CacheRefresh(refresh = 60 , stopRefreshAfterLastAccess = 600 , refreshLockTimeout = 600)
    public WorkName getById(Integer id){

//        StudentBean userServiceImpl = SpringContextHolder.getBean("sebbbBean");
//        String s = userServiceImpl.findByAll();
        System.out.println("W26378");
        return workNameMapper.getByIds(id);
    }

    @Override
    @Transactional
    public void updateById(Map<String,Object> map){
        WorkName W = workNameMapper.getByIds(Integer.valueOf(map.get("id").toString()));
        workNameMapper.updateChargeName(map);
        WorkName W2 = workNameMapper.getByIds(Integer.valueOf(map.get("id").toString()));
        System.out.println("W267");
        StringBuffer sb = new StringBuffer();
        sb.append(1);
    }

    @Override
    public String getAccountMoney() {
        return "30";
    }
}
