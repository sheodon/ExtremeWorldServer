package com.jixianxueyuan.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.mapper.BeanMapper;
import org.springside.modules.web.MediaTypes;

import com.jixianxueyuan.config.HobbyPathConfig;
import com.jixianxueyuan.rest.dto.CourseTaxonomyDTO;
import com.jixianxueyuan.rest.dto.CourseTaxonomysResponseDTO;
import com.jixianxueyuan.rest.dto.MyResponse;
import com.jixianxueyuan.service.CourseTaxonomyService;

@RestController
@RequestMapping(value = "/api/secure/v1/{hobby}/course_taxonomy")
public class CourseTaxonomyRestController
{
	private static Logger logger = LoggerFactory.getLogger(CourseTaxonomyRestController.class);
	
	@Autowired
	CourseTaxonomyService courseTaxonomy;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public MyResponse list(@PathVariable String hobby)
	{
		Long hobbyId = HobbyPathConfig.getHobbyId(hobby);
		
		List<CourseTaxonomyDTO> courseTaxonomyList = BeanMapper.mapList(courseTaxonomy.findByHobby(hobbyId), CourseTaxonomyDTO.class); 
		
		CourseTaxonomysResponseDTO courseTaxonomysResponseDTO = new CourseTaxonomysResponseDTO();
		courseTaxonomysResponseDTO.setCourseTaxonomyList(courseTaxonomyList);
		
		return MyResponse.ok(courseTaxonomysResponseDTO,true);
	}
}
