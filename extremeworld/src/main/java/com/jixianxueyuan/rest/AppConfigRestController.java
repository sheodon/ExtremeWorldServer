package com.jixianxueyuan.rest;

import com.jixianxueyuan.rest.dto.MyResponse;
import com.jixianxueyuan.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

/**
 * Created by sheodon on 2017/2/19.
 */
@RestController
@RequestMapping(value = "/api/v1/{hobby}/app_config")
public class AppConfigRestController {

    @Autowired
    AppConfigService appConfigService;
}
