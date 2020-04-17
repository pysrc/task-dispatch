package com.example.dispatch.task;

import com.alibaba.fastjson.JSON;
import com.example.dispatch.kafka.TaskProducer;

/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
public abstract class TaskRunner {
    protected TaskProducer producer;

    public TaskRunner(TaskProducer producer) {
        this.producer = producer;
    }

    // 下一阶段任务
    protected void next(String stage, Object meta) {
        if (meta instanceof String) {
            producer.pushTask(stage, (String) meta);
        } else {
            String data = JSON.toJSONString(meta);
            producer.pushTask(stage, data);
        }
    }

    // 当前任务处理
    public abstract void run(String object);
}
