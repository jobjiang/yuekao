app.service("orderService",function($http){
	this.searchCourse = function(page,size){
		return $http.get("/order/searchCourse.do?page="+page+"&size="+size);
	}
	this.searchOrder = function(){
		return $http.get("/order/searchOrder.do");
	}
	
	this.typeList = function(){
		return $http.get("/order/typeList.do");
	}
	this.teacherList = function(){
		return $http.get("/order/teacherList.do");
	}
	
	this.uploadFile = function(){
		var formData = new FormData();
		formData.append("file",file.files[0]);
		return $http({
			method:'POST',
			url:'/order/uploadFile.do',
			data:formData,
			headers:{'Content-type':undefined},
			transformRequest:angular.identity
		})
	}
	
	this.save = function(entity){
		return $http.post("/order/save.do",entity);
	}
	
	this.changeStatus = function(entity){
		return $http.post("/order/changeStatus.do",entity);
	}
	
	this.findOneCourse = function(cid){
		return $http.get("/order/findOneCourse.do?cid="+cid);
	}
	
	this.shopping = function(entity){
		return $http.post("/order/shopping.do",entity);
	}
})