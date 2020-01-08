package com.order.entity;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

	private long totalPage;
	
	private List data;

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public PageResult(long totalPage, List data) {
		super();
		this.totalPage = totalPage;
		this.data = data;
	}

	public PageResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
