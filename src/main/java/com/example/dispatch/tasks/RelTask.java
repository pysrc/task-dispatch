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
public class RelTask extends TaskRunner {

    public RelTask(TaskProducer producer) {
        super(producer);
    }

    @Async("taskExecutor")
    @Override
    public void run(String object) {
        // 假设这个阶段很耗时间
        try {
            int i = 1 + (int) (Math.random() * 20);
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("关联关系处理阶段" + object);
        this.next(Constants.IDB, object);
    }
}
