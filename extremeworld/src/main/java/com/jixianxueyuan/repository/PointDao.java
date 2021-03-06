package com.jixianxueyuan.repository;

import java.util.Date;
import java.util.List;

import com.jixianxueyuan.entity.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PointDao extends PagingAndSortingRepository<Point, Long>{

	Page<Point> findByUserId(long userId, Pageable pageable);
	
	@Query("SELECT p FROM Point p WHERE p.user.id=? AND  p.type=? AND p.createTime > ?")
	List<Point> findByTypeAndUserIdAndTime(long userId, String type, Date date);
}
