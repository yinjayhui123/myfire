/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.rocketmq.base;

/**
 * @author yinhui
 * @version ProducerWrapper, v0.1 2018/10/31 15:01
 */
public class ProducerWrapper {

    private String group;

    private String instanceName;

    public String getGroup() {
        return group;
    }

    public void setGroup(Enum group) {
        this.group = group.name();
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
}
