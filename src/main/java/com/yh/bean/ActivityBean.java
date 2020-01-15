package com.yh.bean;

/**
 * @Author: yinhui
 * @Date: 2020/1/15 15:02
 * @Version 1.0
 */
public class ActivityBean {

    //活动id
    private int id;

    //活动名称
    private String activityName;

    //结束时间
    private Long timeEnd;

    public ActivityBean(int id,String activityName,Long timeEnd){
        this.id = id;
        this.activityName = activityName;
        this.timeEnd = timeEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }
}
