<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<<<<<<< HEAD
<link href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<link href="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.css" rel="stylesheet"/> 
<script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form class="form-search form-inline" method="post">
				
				标题：<input class="input-medium search-query" type="text" id="title" name="title"/> 
				URL：<input class="input-medium search-query" type="text" id="url" name="url"/> 
				文章类型：<input class="input-medium search-query" type="text" id="news_type" name="news_type"/> 
				创建时间：<input class="input-medium search-query" type="text" id="news_type" name="create_time"/>
				<br>
				<br><button type="button" class="btn" onclick="doSearch()">查找</button>
			</form>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<table id="table">
			</table>
			<br>
			
		</div>
	</div>
</div>
</body>
</html>

<script type="text/javascript">
$().ready(function(){
var $table = $('#table');
$table.bootstrapTable({
url: "list.json", 
dataType: "json",
pagination: true, //分页
singleSelect: false,
sidePagination: "server", //服务端处理分页
      columns: [
              {
                title: '',
                  field: 'state',
                  align: 'center',
                  checkbox:"true"
              }, 
              {
                  title: '标题',
                  field: 'title',
                  align: 'left',
                  sortable:'true'
              }, 
              {
                  title: 'URL',
                  field: 'url',
                  align: 'left',
                  sortable:'true'
              },
              {
                  title: '文章类型',
                  field: 'news_type',
                  align: 'left',
                  sortable:'true'
              },
              {
                  title: '评论',
                  field: 'cmt_count',
                  align: 'center',
                  sortable:'true'
              },
              {
                  title: '创建时间',
                  field: 'create_time',
                  align: 'left',
                  sortable:'true'
              },
              {
                  title: '爬取时间',
                  field: 'last_update_time',
                  align: 'left',
                  formatter:function(value,row,index){ 
                	  var time=new Date(value);
                	  return time.format("yyyy-MM-dd hh:mm:ss")
                  },
                  sortable:'true'
              }
          ]
  });
});
</script>
=======
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form class="form-search form-inline">
				<input class="input-medium search-query" type="text" /> <button type="submit" class="btn">查找</button>
			</form>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<table id="grid" data-toggle="table"
						data-url="/user/list"
						data-single-select="true"
						data-side-pagination="server"
						data-pagination="true" 
						data-click-to-select="true"
						>
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="index" data-align="center" class="col-sm-1" data-formatter="indexFormatter">序号</th>
								<th data-field="title" data-sortable="true" >标题</th>
							</tr>
						</thead>
					</table>
			<table class="table">
				<thead>
					<tr>
						<th>
							编号
						</th>
						<th>
							产品
						</th>
						<th>
							交付时间
						</th>
						<th>
							状态
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							1
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							01/04/2012
						</td>
						<td>
							Default
						</td>
					</tr>
					<tr class="success">
						<td>
							1
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							01/04/2012
						</td>
						<td>
							Approved
						</td>
					</tr>
					<tr class="error">
						<td>
							2
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							02/04/2012
						</td>
						<td>
							Declined
						</td>
					</tr>
					<tr class="warning">
						<td>
							3
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							03/04/2012
						</td>
						<td>
							Pending
						</td>
					</tr>
					<tr class="info">
						<td>
							4
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							04/04/2012
						</td>
						<td>
							Call in to confirm
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
>>>>>>> branch 'master' of http://54.222.133.82/frank.li/test01.git
