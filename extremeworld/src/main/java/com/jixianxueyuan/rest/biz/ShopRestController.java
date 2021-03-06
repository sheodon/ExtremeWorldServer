/**
 * 
 */
package com.jixianxueyuan.rest.biz;

import com.jixianxueyuan.config.HobbyPathConfig;
import com.jixianxueyuan.entity.AppConfig;
import com.jixianxueyuan.service.AppConfigService;
import com.jixianxueyuan.utils.TargetInfo;
import com.jixianxueyuan.entity.biz.Shop;
import com.jixianxueyuan.rest.dto.MyPage;
import com.jixianxueyuan.rest.dto.MyResponse;
import com.jixianxueyuan.rest.dto.biz.ShopDTO;
import com.jixianxueyuan.service.biz.ShopService;
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


/**
 * @author pengchao
 *
 */
@RestController
@RequestMapping(value = "/api/secure/v1/{hobby}/biz/shop")
public class ShopRestController {
	private static Logger logger = LoggerFactory.getLogger(ShopRestController.class);
	
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	ShopService shopService;

	@Autowired
	AppConfigService appConfigService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public MyResponse list(@PathVariable String hobby,
						   @RequestParam(value = "page", defaultValue = "1") int pageNumber,
						   @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
						   @RequestParam(value = "sortType", defaultValue = "auto") String sortType,
						   @RequestParam(value = "targetInfo", required = false) String targetInfo){

		Long hobbyId = HobbyPathConfig.getHobbyId(hobby);
		AppConfig appConfig = appConfigService.getAppConfig(hobbyId);

		if (TargetInfo.isIOSAppVersion(targetInfo, appConfig.getIosAppVersionLimit())) {
			MyPage<ShopDTO,Shop> myShopPage = new MyPage<ShopDTO,Shop>();
			return MyResponse.ok(myShopPage);
		}

		Page<Shop> shopPage = shopService.getAllByHobbyId(hobbyId, pageNumber, pageSize, sortType);
		MyPage<ShopDTO,Shop> myShopPage = new MyPage<ShopDTO,Shop>(ShopDTO.class, shopPage);
		
		return MyResponse.ok(myShopPage);
	}
	

	
}
