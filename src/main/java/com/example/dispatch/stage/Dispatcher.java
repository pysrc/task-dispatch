package com.example.dispatch.stage;

import com.example.dispatch.constants.Constants;
import com.example.dispatch.tasks.IdbTask;
import com.example.dispatch.tasks.RelTask;
import com.example.dispatch.tasks.XmlTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
@Component
public class Dispatcher {
    @Autowired
    private IdbTask idbTask;
    @Autowired
    private RelTask relTask;
    @Autowired
    private XmlTask xmlTask;

    @Async("taskExecutor")
    public void runTask(String task) {
        String[] split = task.split("\\|", 2);
        assert split.length == 2;
        String taskId = split[0];
        String data = split[1];
        // 这里可以再一次用线程池异步
        switch (taskId) {
            case Constants.XML:
                xmlTask.run(data);
                break;
            case Constants.REL:
                relTask.run(data);
                break;
            case Constants.IDB:
                idbTask.run(data);
                break;
        }
    }
}
