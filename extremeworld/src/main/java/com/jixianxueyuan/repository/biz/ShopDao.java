package com.jixianxueyuan.repository.biz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jixianxueyuan.entity.biz.Shop;

public interface ShopDao extends  PagingAndSortingRepository<Shop,Long>{

	public Shop findByUserId(Long userId);
	
	@Query("SELECT t FROM Shop t LEFT JOIN t.hobbys h WHERE h.id=?")
	public Page<Shop> findByHobby(Long hobbyId, Pageable pageable);
}
