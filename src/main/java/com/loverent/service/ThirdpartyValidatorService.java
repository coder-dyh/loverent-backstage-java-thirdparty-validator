package com.loverent.service;

import com.alibaba.fastjson.JSON;
import com.loverent.enums.ThirdPartyInterfaceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dengyuanhua
 * @descrition
 * @date 2022-05-20 11:38:34
 * @since 1.0.0
 */
@Service
@Slf4j
public class ThirdpartyValidatorService {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private RestTemplate restTemplate;



    public void validate() {
        // 简单调用
        List<ThirdPartyInterfaceEnum> list = ThirdPartyInterfaceEnum.getAll();
        if (!CollectionUtils.isEmpty(list)) {
            for (ThirdPartyInterfaceEnum thirdPartyInterfaceEnum : list) {
                try {
                    log.info("开始从三方服务{}调用三方接口执行【{}】，请求地址：{}", thirdPartyInterfaceEnum.getProjectName(), thirdPartyInterfaceEnum.getDesc(), thirdPartyInterfaceEnum.getRequestUrl());
                    ResponseEntity reqResult = restTemplate.exchange(thirdPartyInterfaceEnum.getRequestUrl(), HttpMethod.resolve(thirdPartyInterfaceEnum.getRequestType()), new HttpEntity(new HashMap<>()), String.class);
                    log.info("调用结果：{}", JSON.toJSONString(reqResult));
                } catch (Exception e) {
                    log.error("调用三方接口异常，接口信息：项目名={}，接口地址={}，接口用途={}，原因：{}", thirdPartyInterfaceEnum.getProjectName(), thirdPartyInterfaceEnum.getRequestUrl(), thirdPartyInterfaceEnum.getDesc(), e);
                }
            }
        }

        // 复杂调用
        Map<String, ThirdPartyValidService> thirdPartyValidServiceMap = applicationContext.getBeansOfType(ThirdPartyValidService.class);
        if (!CollectionUtils.isEmpty(thirdPartyValidServiceMap)) {
            for (String key : thirdPartyValidServiceMap.keySet()) {
                ThirdPartyValidService thirdPartyValidService = thirdPartyValidServiceMap.get(key);
                ThirdPartyInterfaceEnum thirdPartyInterfaceEnum = thirdPartyValidService.getThirdPartyInfo();
                try {
                    Object executeResult = thirdPartyValidService.executeRequest();
                    log.error("接口信息：项目名={}，接口地址={}，接口用途={}的调用结果为：{}", thirdPartyInterfaceEnum.getProjectName(), thirdPartyInterfaceEnum.getRequestUrl(), thirdPartyInterfaceEnum.getDesc(), JSON.toJSONString(executeResult));
                } catch (Exception e) {
                    log.error("调用三方接口异常，接口信息：项目名={}，接口地址={}，接口用途={}，原因：{}", thirdPartyInterfaceEnum.getProjectName(), thirdPartyInterfaceEnum.getRequestUrl(), thirdPartyInterfaceEnum.getDesc(), e);
                }
            }
        }


    }

}
