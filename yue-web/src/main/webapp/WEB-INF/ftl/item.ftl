<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head><script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="css2/index2.css" ></link>
<body>
	<table>
		<tr>
			<td>课程名称</td>
			<td>${entity.cname}</td>
		</tr>
		<tr>
			<td>课程图片</td>
			<td>
				<img alt="" src="${entity.images}" width="40px" height="40px">
			</td>
		</tr>
		<tr>
			<td>课程类别</td>
			<td>${entity.tid}</td>
		</tr>
		<tr>
			<td>课时</td>
			<td>${entity.price}</td>
		</tr>
		<tr>
			<td>讲课老师</td>
			<td>${entity.sid}</td>
		</tr>
		<tr>
			<td>开课日期</td>
			<td>${entity.createtime}</td>
		</tr>
	</table>
</body>
</html>