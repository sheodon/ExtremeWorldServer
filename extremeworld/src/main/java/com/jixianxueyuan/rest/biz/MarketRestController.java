package com.jixianxueyuan.rest.biz;

import java.util.List;

import com.jixianxueyuan.config.HobbyPathConfig;
import com.jixianxueyuan.entity.biz.Category;
import com.jixianxueyuan.rest.dto.MyResponse;
import com.jixianxueyuan.rest.dto.biz.CategoryDTO;
import com.jixianxueyuan.rest.dto.biz.MarketDTO;
import com.jixianxueyuan.service.biz.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.mapper.BeanMapper;
import org.springside.modules.web.MediaTypes;


@RestController
@RequestMapping(value = "/api/secure/v1/{hobby}/biz/market")
public class MarketRestController {

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	MyResponse list(@PathVariable String hobby){
		
		Long hobbyId = HobbyPathConfig.getHobbyId(hobby);
		List<Category> categoryList = categoryService.getAllByHobbyId(hobbyId);
		List<CategoryDTO> categoryDTOList = BeanMapper.mapList(categoryList, CategoryDTO.class);
		
		MarketDTO storeInfo = new MarketDTO();
		storeInfo.setCategoryList(categoryDTOList);
		
		return MyResponse.ok(storeInfo);
		
	}
}
