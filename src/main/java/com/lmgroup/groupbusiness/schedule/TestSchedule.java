package com.lmgroup.groupbusiness.schedule;


import com.lmgroup.groupbusiness.domain.business.BussinessVO;
import com.lmgroup.groupbusiness.service.BussinessService;
import com.lmgroup.groupbusiness.utils.commonAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务测试
 *
 * @author wangzichun 时间:2018/11/05
 */
@Configuration
@EnableScheduling
@RestController
public class TestSchedule extends commonAction {

    @Autowired
    private BussinessService bussinessService;


    /**
     * 每天早上10：15触发
     *
     * @return
     * @throws Exception
     */
/*
    @Scheduled(cron = "0 15 11 * * ?")
*/
    public BussinessVO query() throws Exception {
        int id = 1;
        BussinessVO bussinessVO = bussinessService.data(id);
        return bussinessVO;
    }
}
