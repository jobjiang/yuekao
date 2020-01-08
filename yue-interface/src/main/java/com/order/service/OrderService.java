package com.order.service;

import java.util.List;

import com.order.entity.PageResult;
import com.order.entity.Result;
import com.order.pojo.TbCourse;
import com.order.pojo.TbOrder;
import com.order.pojo.TbTeacher;
import com.order.pojo.TbType;
import com.order.pojo.TbUser;

public interface OrderService {

	//登录
	public TbUser findByRole(String username);
	
	//课程列表数据查询
	public PageResult searchCourse(int page,int size);
	//课程类别下拉框
	public List<TbType> typeList();
	//讲课讲师下拉框
	public List<TbTeacher> teacherList();
	//保存课程信息
	public boolean save(TbCourse course);
	
	//修改课程状态
	public boolean changeStatus(TbCourse course);
	//查找课程单条数据
	public TbCourse findOneCourse(int cid);
	//订单页面查询
	public Result searchOrder();
	//提交订单
	public boolean shopping(TbOrder order);
}
