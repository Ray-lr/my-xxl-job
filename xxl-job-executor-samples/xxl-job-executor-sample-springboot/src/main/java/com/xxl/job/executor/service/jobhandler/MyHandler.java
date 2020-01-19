package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.mvc.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/***
 * 类的描述: 我的jobHandler
 *
 * @author : lirui
 * @date : 2020/1/16 20:51
 */
@Component
public class MyHandler {
    /**
    * 日志
    */
    private static Logger logger = LoggerFactory.getLogger(MyHandler.class);
    @Resource
    private MyService myService;

    /***
     * 功能描述: 我的定时任务
     *
     * @param name: 预设传参，或手动执行时传参
     * @return : 成功200,失败500
     * @author : Ray
     * @date : 2020/1/16 20:43
     */
    @XxlJob(value = "myJobHandler",init = "init", destroy = "destroy")
    public ReturnT<String> myJobHandler(String name) {
        String result =myService.sayHello(name);
        // 说的内容为空，程序执行异常
        if (StringUtils.isEmpty(result)) {
            XxlJobLogger.log("no one said anything!");
            return ReturnT.FAIL;
            // name的值是Ray，不是等于入参，即没传参，判定为失败
        } else if(!myService.getName().equals(name)) {
            XxlJobLogger.log(result);
            return new ReturnT<>(500,"业务执行失败，未传参数");
            // 传了参数，判定成功
        } else {
            XxlJobLogger.log(result);
            return new ReturnT<>(200,"业务执行成功！");
        }
    }
    public void init() {
        String before = "init myJobHandler!";
        XxlJobLogger.log(before);
        System.out.println(before);
    }
    public void destroy() {
        String after = "destroy myJobHandler!";
        XxlJobLogger.log(after);
        System.out.println(after);
    }
}
