package com.yh.dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *本地线程全局变量
 * @Author: yinhui
 * @Date: 2019/4/24 10:42
 * @Version 1.0
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static List<String> slaveDataSourceKey = new ArrayList<>();

    /**
     * 用于轮循的计数器
     */
    private static int counter = 0;

    /**
     * 用于在切换数据源时保证不会被其他线程修改
     */
    private static Lock lock = new ReentrantLock();

    // 列举数据源的key
    public enum DataSourceType{
        WRITE , READONE ,READTWO;
    }

    /**
     * 手动设置数据源
     * @param key
     */
    public static void setDataSourceKey(String key){
        CONTEXT_HOLDER.set(key);
    }

    public static void useWrite(){
        CONTEXT_HOLDER.set(DataSourceType.WRITE.name());
    }

    public static void useReadOne(){
        CONTEXT_HOLDER.set(DataSourceType.READONE.name());
    }

    public static void useReadTwo(){
        CONTEXT_HOLDER.set(DataSourceType.READTWO.name());
    }

    /**
     * 获得当前线程数据源连接
     * @return
     */
    public static String getDataSourceKey(){
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除当前线程(remove()方法移除当前前程的副本变量值)
     */
    public static void clearDataSourceKey(){
        CONTEXT_HOLDER.remove();
    }

    /**
     * 当使用只读数据源时通过轮循方式选择要使用的数据源
     */
    public static void useSlaveDataSource(){
        lock.lock();

        try {
            int datasourceKeyIndex = counter % slaveDataSourceKey.size();
            CONTEXT_HOLDER.set(slaveDataSourceKey.get(datasourceKeyIndex));
            if(counter < 0) {
                counter = 0;
            }else {
                counter++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
