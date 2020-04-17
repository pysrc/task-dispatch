package com.example.dispatch.tasks;

import com.alibaba.fastjson.JSON;
import com.example.dispatch.constants.Constants;
import com.example.dispatch.kafka.TaskProducer;
import com.example.dispatch.task.TaskRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
@Component
public class XmlTask extends TaskRunner {
    private Pattern op = Pattern.compile("<O>(.*?)</O>");
    private Pattern data = Pattern.compile("<C>(.+?)</C><D>(.*?)</D>");

    public XmlTask(TaskProducer producer) {
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
        Map<String, String> res = new HashMap<>();
        String xml = object;
        Matcher opm = op.matcher(xml);
        Matcher datam = data.matcher(xml);
        if (opm.find()) {
            res.put("op", opm.group(1));
        }
        while (datam.find()) {
            res.put(datam.group(1), datam.group(2));
        }
        String json = JSON.toJSONString(res);
        System.out.println("xml解析阶段" + json);
        this.next(Constants.REL, res);
    }
}
