package com.jixianxueyuan.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.mapper.BeanMapper;
import org.springside.modules.web.MediaTypes;

import com.jixianxueyuan.entity.Province;
import com.jixianxueyuan.rest.dto.ProvinceDTO;
import com.jixianxueyuan.service.ProvinceService;

@RestController
@RequestMapping(value = "/api/secure/v1//province")
public class ProvinceRestController
{
	private static Logger logger = LoggerFactory.getLogger(ProvinceRestController.class);
	
	@Autowired
	ProvinceService provinceService;
	
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<ProvinceDTO> list()
	{
		List<Province> provinceEntitys = provinceService.getAll();
		
		List<ProvinceDTO> provinceDTOs = BeanMapper.mapList(provinceEntitys, ProvinceDTO.class);
		
		return provinceDTOs;
	}

}
