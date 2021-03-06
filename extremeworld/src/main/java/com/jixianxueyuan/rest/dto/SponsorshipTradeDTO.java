package com.jixianxueyuan.rest.dto;

import java.util.Date;

public class SponsorshipTradeDTO {
	private Long id;
	private double sum;
	private String message;
	private Date createTime;
	
	private UserMinDTO user;
	private TradeDTO trade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserMinDTO getUser() {
		return user;
	}

	public void setUser(UserMinDTO user) {
		this.user = user;
	}

	public TradeDTO getTrade() {
		return trade;
	}

	public void setTrade(TradeDTO trade) {
		this.trade = trade;
	}
}
