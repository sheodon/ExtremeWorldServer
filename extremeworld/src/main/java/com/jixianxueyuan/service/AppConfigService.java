package com.jixianxueyuan.service;

import com.jixianxueyuan.entity.AppConfig;
import com.jixianxueyuan.repository.AppConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sheodon on 2017/2/19.
 */
@Component
@Transactional
public class AppConfigService {

    @Autowired
    private AppConfigDao appConfigDao;

    public AppConfig getAppConfig(Long id) {
        return  appConfigDao.findOne(id);
    }
}
