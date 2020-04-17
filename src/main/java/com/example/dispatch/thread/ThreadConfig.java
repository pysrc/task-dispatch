package com.example.dispatch.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
@Configuration
@EnableAsync
public class ThreadConfig {
    @Autowired
    private TreadConfigValue value;

    @Bean("taskExecutor")
    public Executor taskExecutro() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(value.getCorePoolSize());
        taskExecutor.setMaxPoolSize(value.getMaxPoolSize());
        taskExecutor.setQueueCapacity(value.getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(value.getKeepAliveSeconds());
        taskExecutor.setThreadNamePrefix(value.getThreadNamePrefix());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(value.isWaitForTasksToCompleteOnShutdown());
        taskExecutor.setAwaitTerminationSeconds(value.getAwaitTerminationSeconds());
        return taskExecutor;
    }
}
