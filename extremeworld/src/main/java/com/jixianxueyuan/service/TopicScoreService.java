package com.jixianxueyuan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixianxueyuan.entity.TopicScore;
import com.jixianxueyuan.repository.TopicScoreDao;

@Component
@Transactional
public class TopicScoreService {
	
	private TopicScoreDao topicScoreDao;

	public TopicScore findByTopicIdAndUserId(Long topicId, Long userId){
		return topicScoreDao.findByTopicIdAndUserId(topicId, userId);
	}

	public void save(TopicScore topicScore){
		topicScoreDao.save(topicScore);
	}
	
	public Page<TopicScore> findAllByTopicId(Long topicId,int pageNumber, int pageSize,String sortType){
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		return topicScoreDao.findByTopicId(topicId, pageRequest);
	}
	
	public double getTopicAvgScore(Long topicId){
		return topicScoreDao.getTopicAvgScore(topicId);
	}
	
	public int getTopicScoreCount(Long topicId){
		return topicScoreDao.getTopicScoreCount(topicId);
	}

	@Autowired
	public void setTopicScoreDao(TopicScoreDao topicScoreDao) {
		this.topicScoreDao = topicScoreDao;
	}
	
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		}else{
			sort = new Sort(Direction.DESC, "id");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

}
