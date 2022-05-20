package com.loverent.service;

import com.loverent.enums.ThirdPartyInterfaceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author dengyuanhua
 * @descrition
 * @date 2022-05-20 11:25:43
 * @since 1.0.0
 */
public interface ThirdPartyValidService {


    /**
     * 获取接口信息
     * @return
     */
    ThirdPartyInterfaceEnum getThirdPartyInfo();


    /**
     * 执行请求
     * @return
     */
    Object executeRequest();

}
