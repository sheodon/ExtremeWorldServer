package com.jixianxueyuan.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.jixianxueyuan.entity.Remind;
import com.jixianxueyuan.rest.dto.MyPage;
import com.jixianxueyuan.rest.dto.MyResponse;
import com.jixianxueyuan.rest.dto.RemindDTO;
import com.jixianxueyuan.service.RemindService;

@RestController
@RequestMapping(value = "/api/secure/v1/remind")
public class RemindRestController {

	private static Logger logger = LoggerFactory.getLogger(RemindRestController.class);
	
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	RemindService remindService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public MyResponse list(
			@PathVariable("id") Long id,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType){
		
		Page<Remind> remindList = remindService.getAll(id,pageNumber, pageSize, sortType);
		
		MyPage<RemindDTO, Remind> myRemindPage = new MyPage<RemindDTO, Remind>(RemindDTO.class, remindList);
		
		return MyResponse.ok(myRemindPage,true);
		
	}
	
}
