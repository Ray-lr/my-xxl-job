package com.xxl.job.executor.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/***
 * 类的描述: 我的业务
 *
 * @author : lirui
 * @date : 2020/1/16 20:49
 */

@Service
public class MyService {
    private String name;
    private String words;

    public String sayHello(String name) {
        // 如果手动执行未传参数,name默认为Ray
        if (StringUtils.isEmpty(name)) {
            this.setName("Ray");
        } else {
            this.setName(name);
        }
        this.setUp();
        return this.getName()+" says：" + this.getWords();
    }
    public void setUp() {
        this.setWords("Hello everyOne!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
