package com.order.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.alibaba.dubbo.config.annotation.Reference;
import com.order.entity.PageResult;
import com.order.entity.Result;
import com.order.pojo.TbCourse;
import com.order.pojo.TbOrder;
import com.order.pojo.TbUser;
import com.order.service.OrderService;
import com.order.utils.AliyunOSSClientUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Reference
	OrderService orderService;
	
	@Autowired
	FreeMarkerConfig freeMarkerConfig;
	/**
	 * 通过用户角色判断
	 * @return  返回布尔值
	 */
	@RequestMapping("/findUserRole")
	public boolean findUserRole(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		TbUser role = orderService.findByRole(name);
		if(role.getRole().equals("0")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @param page  分页单位
	 * @param size  分页大小
	 * @return  返回分页查询结果
	 */
	@RequestMapping("/searchCourse")
	public PageResult searchCourse(int page,int size){
		return orderService.searchCourse(page, size);
	}
	//课程类别下拉框
	/**
	 * @return  课程类别下拉框查询
	 */
	@RequestMapping("/typeList")
	public Result typeList(){
		return new Result(true, orderService.typeList());
	}
	//讲课讲师下拉框
	/**
	 * @return  讲师下拉框查询
	 */
	@RequestMapping("/teacherList")
	public Result teacherList(){
		return new Result(true, orderService.teacherList());
	}
	
	//上传图片
	/**
	 * @param file 前台文件
	 * @return  返回图片路径
	 * @throws Exception
	 */
	@RequestMapping("/uploadFile")
	public Result uploadFile(MultipartFile file) throws Exception{
		AliyunOSSClientUtils oss = new AliyunOSSClientUtils();
		String img2Oss = oss.uploadImg2Oss(file);
		String imgUrl = oss.getImgUrl(img2Oss);
		return new Result(true, imgUrl);
	}
	
	//保存课程
	/**
	 * @param course  课程信息
	 * @return  返回布尔值
	 */
	@RequestMapping("/save")
	public boolean save(@RequestBody TbCourse course){
		return orderService.save(course);
	}
	
	//修改课程状态
	/**
	 * @param course  前台传过来课程信息  改变状态
	 * @return  返回布尔
	 */
	@RequestMapping("/changeStatus")
	public boolean changeStatus(@RequestBody TbCourse course){
		boolean status = orderService.changeStatus(course);
		if(status){
			getHtml(course.getCid());
		}
		return status;
	}
	
	//生成静态页面
	/**
	 * @param cid  传入cid 生成相对应静态页面
	 */
	public void getHtml(int cid){
		Configuration configuration = freeMarkerConfig.getConfiguration();
		try {
			Template template = configuration.getTemplate("item.ftl");
			Map map = new HashMap();
			TbCourse course = orderService.findOneCourse(cid);
			map.put("entity", course);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:/item/"+cid+".html"), "utf-8"));
			template.process(map, out);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//订单页面查询
	/**
	 * @return  订单查询
	 */
	@RequestMapping("/searchOrder")
	public Result searchOrder(){
		return orderService.searchOrder();
	}
	
	//课程数据回显
	/**
	 * @param cid  通过课程id  回显课程信息
	 * @return  返回课程信息
	 */
	@RequestMapping("/findOneCourse")
	public Result findOneCourse(int cid){
		return new Result(true, orderService.findOneCourse(cid));
	}
	
	//新增订单
	/**
	 * @param order前台订单信息
	 * @return  返回布尔值  判断成功与否
	 */
	@RequestMapping("/shopping")
	public boolean shopping(@RequestBody TbOrder order){
		return orderService.shopping(order);
	}
}
