app.controller("orderController",function($scope,orderService,$location){
	//初始化分页单位  大小  总页数
	$scope.page = 1;
	$scope.size = 2;
	$scope.totalPage = 10;
	
	//课程类别
	$scope.typeZ = ['','后端','前端'];
	//讲师
	$scope.teacherZ = ['','张三','李四'];
	//课程状态
	$scope.statusZ = ['未发布','已发布'];
	//修改状态
	$scope.xiuZ = ['上架','下架'];
	//订单状态
	$scope.orderZ = ['未支付','已支付'];
	
	$scope.entity ={};
	
	$scope.prev = function(){
		$scope.page = $scope.page-1;
		$scope.searchCourse();
	}
	$scope.next = function(){
		$scope.page = $scope.page+1;
		$scope.searchCourse();
	}
	
	//课程列表页面数据查询
	$scope.searchCourse = function(){
		orderService.searchCourse($scope.page,$scope.size).success(function(req){
			$scope.courseList = req.data;
			$scope.totalPage = req.totalPage;
		})
	}
	//订单列表页面数据查询
	$scope.searchOrder = function(){
		orderService.searchOrder().success(function(req){
			$scope.orderList = req.data;
		})
	}
	//课程类别下拉框
	$scope.typeList = function(){
		orderService.typeList().success(function(req){
			$scope.tlist = req.data;
		})
	}
	//讲课讲师下拉框
	$scope.teacherList = function(){
		orderService.teacherList().success(function(req){
			$scope.slist = req.data;
		})
	}
	//图片上传
	$scope.uploadFile = function(){
		orderService.uploadFile().success(function(req){
			$scope.entity.images = req.data;
		})
	}
	//保存新增课程信息
	$scope.save = function(){
		orderService.save($scope.entity).success(function(req){
			if(req){
				console.log("ok");
				location.href = "course.html";
			}else{
				alert("no ok");
			}
		})
	}
	
	//修改课程上下架状态
	$scope.changeStatus = function(entity){
		orderService.changeStatus(entity).success(function(req){
			if(req){
				console.log("ok");
				$scope.searchCourse();
			}else{
				alert("no ok");
			}
		})
	}
	var cid = $location.search()['cid'];
	//回显课程数据
	$scope.findOneCourse = function(){
		orderService.findOneCourse(cid).success(function(req){
			$scope.entity = req.data;
		})
	}
	//设置监听   单价  得出总金额
	$scope.$watch('entity.price',function(newV,oldV){
		$scope.entity.sum = $scope.entity.price;
	})
	
	//提交订单
	$scope.shopping = function(){
		orderService.shopping($scope.entity).success(function(req){
			if(req){
				console.log("ok");
				location.href = "order.html";
			}else{
				alert("no ok");
			}
		})
	}
	
})