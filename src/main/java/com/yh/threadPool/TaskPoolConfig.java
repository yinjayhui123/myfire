package com.yh.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池
 * @Author: yinhui
 * @Date: 2019/7/12 11:47
 * @Version 1.0
 */
@Configuration
@EnableAsync
public class TaskPoolConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        //核心线程数5：线程池创建时候初始化的线程数
        poolExecutor.setCorePoolSize(5);
        //最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        poolExecutor.setMaxPoolSize(20);
        //缓冲队列200：用来缓冲执行任务的队列
        poolExecutor.setQueueCapacity(200);
        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        poolExecutor.setThreadNamePrefix("taskExecutor-");
        return poolExecutor;
    }
}
