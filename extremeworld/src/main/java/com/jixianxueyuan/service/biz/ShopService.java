package com.jixianxueyuan.service.biz;

import com.jixianxueyuan.entity.biz.Shop;
import com.jixianxueyuan.repository.biz.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


//Spring Bean的标识.
@Component
//类中所有public函数都纳入事务管理的标识.
@Transactional
public class ShopService {
	
	@Autowired
	ShopDao shopDao;
	
	public Shop findById(Long id){
		return shopDao.findOne(id);
	}
	
	public Shop findByUserId(Long userId){
		return shopDao.findByUserId(userId);
	}
	
	public Page<Shop> getAll(int pageNumber, int pageSize,String sortType){
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		return shopDao.findAll(pageRequest);
	}
	
	public Page<Shop> getAllByHobbyId(long hobbyId, int pageNumber, int pageSize,String sortType){
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		return shopDao.findByHobby(hobbyId,pageRequest);
	}
	
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "weight");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
}
