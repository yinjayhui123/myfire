package com.yh.dao;

import com.yh.bean.WorkName;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author: yinhui
 * @Date: 2019/4/24 13:43
 * @Version 1.0
 */
public interface WorkNameMapper {

    WorkName getByIds(int id);

    void updateChargeName(Map<String,Object> map);

}
