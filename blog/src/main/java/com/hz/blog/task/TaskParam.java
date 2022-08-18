package com.hz.blog.task;


import com.hz.blog.model.ModelBeanMap;

/**
 * 封装任务参数类
 * @author zengjintao
 * @version 1.0
 * @create_at 2020/4/13 14:46
 */
public class TaskParam extends ModelBeanMap {

    private Class<? extends TaskListener> taskListenerClass;
    private final long timestamp;
    private String queueName;

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public TaskParam(String queueName) {
        this.queueName = queueName;
        this.timestamp = System.currentTimeMillis();
    }


    public TaskParam(Class<? extends TaskListener> taskListenerClass) {
        this(taskListenerClass, null);
    }

    public TaskParam(Class<? extends TaskListener> taskListenerClass, long timestamp) {
        this.timestamp = timestamp;
        this.taskListenerClass = taskListenerClass;
    }

    public TaskParam(Class<? extends TaskListener> taskListenerClass, Object data) {
        this(taskListenerClass, System.currentTimeMillis());
    }

    public Class<? extends TaskListener> getTaskListenerClass() {
        return taskListenerClass;
    }
}
