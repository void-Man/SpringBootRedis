package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.CacheOperationService;
import com.example.demo.vo.SPELBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/1
 */
@RestController
@RequestMapping("/redisTester")
public class RedisTesterController {

    private final CacheOperationService cacheOperationService;
    private final ApplicationContext applicationContext;

    public RedisTesterController(CacheOperationService cacheOperationService, ApplicationContext applicationContext) {
        this.cacheOperationService = cacheOperationService;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/getRedisKey")
    public String getRedisKey(String key) {
        return (String) cacheOperationService.get(key);
    }

    @PostMapping("/setRedisKey")
    public String getRedisKey(String key, String value) {
        cacheOperationService.set("user:id","1234567890");
        return (String) cacheOperationService.get(key);
    }

    @PostMapping("/delRedisKey")
    public String delRedisKey(String key) {
        cacheOperationService.del(key);
        return "成功";
    }

    @GetMapping("/testSpelBean")
    public String testSpelBean() {
        SPELBean spelBean = applicationContext.getBean(SPELBean.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("inttValue",spelBean.getIntValue());
        jsonObject.put("strValue",spelBean.getStrValue());
        jsonObject.put("randomIntl",spelBean.getRandomIntl());
        jsonObject.put("nomalContext",spelBean.getNomalContext());
        jsonObject.put("rootContext",spelBean.getRootContext());
        return jsonObject.toJSONString();
    }



}
