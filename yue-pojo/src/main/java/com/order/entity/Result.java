package com.order.entity;

import java.io.Serializable;

public class Result implements Serializable {

	private boolean flag;
	
	private Object data;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result(boolean flag, Object data) {
		super();
		this.flag = flag;
		this.data = data;
	}

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
