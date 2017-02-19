package com.jixianxueyuan.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jixianxueyuan.entity.AppConfig;

/**
 * Created by sheodon on 2017/2/19.
 */
public interface AppConfigDao extends PagingAndSortingRepository<AppConfig, Long> {

}
