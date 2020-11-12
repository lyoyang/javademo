package com.lyoyang.redis;

import java.util.HashMap;
import java.util.Map;

public class Funnel {

    private int capacity;

    private float leakingRate;

    private int leftQuota;

    private long leakingTs;

    public Funnel(int capacity, float leakingRate) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = capacity;
        this.leakingTs = System.currentTimeMillis();
    }

    public void makeSpace() {
        long nowTime = System.currentTimeMillis();
        long deltaTime = nowTime - leakingTs;
        int deltaQuota = (int) (deltaTime * leakingRate);
        //间隔时间太长,整数溢出
        if (deltaQuota < 0) {
            this.leftQuota = capacity;
            this.leakingTs = nowTime;
            return;
        }
        if (deltaQuota < 1) {
            return;
        }
        this.leftQuota += deltaQuota;
        this.leakingTs = nowTime;
        if (this.leftQuota > capacity) {
            this.leftQuota = capacity;
        }

    }

    public boolean watering(int quota) {
        makeSpace();
        if (this.leftQuota >= quota) {
            this.leftQuota -= quota;
            return true;
        }
        return false;
    }

    private Map<String, Funnel> funnels = new HashMap<>();

    public boolean isAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1);

    }



}
