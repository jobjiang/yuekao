package com.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.entity.PageResult;
import com.order.entity.Result;
import com.order.mapper.TbCourseMapper;
import com.order.mapper.TbOrderMapper;
import com.order.mapper.TbTeacherMapper;
import com.order.mapper.TbTypeMapper;
import com.order.mapper.TbUserMapper;
import com.order.pojo.TbCourse;
import com.order.pojo.TbOrder;
import com.order.pojo.TbTeacher;
import com.order.pojo.TbType;
import com.order.pojo.TbUser;
import com.order.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	TbUserMapper userMapper;
	
	@Autowired
	TbCourseMapper courseMapper;
	
	@Autowired
	TbOrderMapper orderMapper;
	
	@Autowired
	TbTeacherMapper teacherMapper;
	
	@Autowired
	TbTypeMapper typeMapper;

	@Override
	public TbUser findByRole(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(username);
	}

	@Override
	public PageResult searchCourse(int page, int size) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, size);
		PageInfo<TbCourse> info = new PageInfo<TbCourse>(courseMapper.selectByExample(null));
		return new PageResult(info.getTotal(), info.getList());
	}

	@Override
	public List<TbType> typeList() {
		// TODO Auto-generated method stub
		return typeMapper.selectByExample(null);
	}
	@Override
	public List<TbTeacher> teacherList() {
		// TODO Auto-generated method stub
		return teacherMapper.selectByExample(null);
	}

	@Override
	public boolean save(TbCourse course) {
		// TODO Auto-generated method stub
		try {
			course.setStatus("0");
			String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
			course.setCreatetime(format);
			courseMapper.insert(course);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean changeStatus(TbCourse course) {
		// TODO Auto-generated method stub
		try {
			if(course.getStatus().equals("0")){
				//未发布  
				course.setStatus("1");
			}else{
				course.setStatus("0");
			}
			courseMapper.updateByPrimaryKey(course);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TbCourse findOneCourse(int cid) {
		// TODO Auto-generated method stub
		return courseMapper.selectByPrimaryKey(cid);
	}

	@Override
	public Result searchOrder() {
		// TODO Auto-generated method stub
		return new Result(true, orderMapper.selectByExample(null));
	}

	@Override
	public boolean shopping(TbOrder order) {
		// TODO Auto-generated method stub
		try {
			String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
			order.setEndtime(format);
			order.setStatus("0");
			orderMapper.insert(order);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}
}
