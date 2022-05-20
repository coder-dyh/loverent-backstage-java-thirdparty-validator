package com.loverent.enums;

import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;

/**
 * @author dengyuanhua
 * @descrition
 * @date 2022-05-20 11:35:23
 * @since 1.0.0
 */
public enum ThirdPartyInterfaceEnum {


    BAIDU("http://www.baidu.com", "app", "百度搜索", "get"),
    ;

    private String requestUrl;

    private String requestType;


    private String desc;

    private String projectName;


    ThirdPartyInterfaceEnum(String requestUrl, String desc, String projectName, String requestType) {
        this.requestUrl = requestUrl;
        this.desc = desc;
        this.projectName = projectName;
        this.requestType = requestType;
    }

    public static List<ThirdPartyInterfaceEnum> getAll() {
        return Lists.newArrayList(values());
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getRequestType() {
        return requestType.toUpperCase();
    }
}
