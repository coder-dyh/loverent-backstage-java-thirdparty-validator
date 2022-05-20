package com.loverent.controller;

import com.loverent.service.ThirdpartyValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengyuanhua
 * @descrition
 * @date 2022-05-20 11:23:25
 * @since 1.0.0
 */
@RestController
@RequestMapping("/third")
public class ThirdPartyValidController {

    @Autowired
    private ThirdpartyValidatorService thirdpartyValidatorService;

    @RequestMapping("/ipWhiteListValidator")
    public ResponseEntity ipWhiteListValidator() {
        thirdpartyValidatorService.validate();
        return ResponseEntity.ok("测试完成！");
    }
}
