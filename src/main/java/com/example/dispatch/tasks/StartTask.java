package com.example.dispatch.tasks;

import com.example.dispatch.constants.Constants;
import com.example.dispatch.kafka.TaskProducer;
import com.example.dispatch.task.TaskRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
@Component
public class StartTask extends TaskRunner {
    public StartTask(TaskProducer producer) {
        super(producer);
    }

    @Async("taskExecutor")
    @Override
    public void run(String object) {
        System.out.println("初始化任务" + object);
        this.next(Constants.XML, object);
    }
}
