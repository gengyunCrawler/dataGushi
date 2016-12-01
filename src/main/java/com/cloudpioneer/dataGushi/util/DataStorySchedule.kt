package com.cloudpioneer.dataGushi.util

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Created by Tijun on 2016/11/29.
 * @author TijunWang
 */
@Component
class DataStorySchedule {
    // 每五秒执行一次
    @Scheduled(cron = "0/5 * * * * ?")
    fun TaskJob() {
        //System.out.print("job start");
    }
}