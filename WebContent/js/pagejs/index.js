$(document).ready(function() {
	var $table = $('#table');
	$table.bootstrapTable({
		url: "list.json", 
		dataType: "json",
		pagination: true, //分页
		singleSelect: false,
		sidePagination: "server", //服务端处理分页
		      columns: [{title: '',field: 'state',align: 'center',checkbox:"true"},
		                {title: '标题',field: 'title',align: 'left',sortable:'true'},
		                {title: 'URL',field: 'url',align: 'left',sortable:'true',
		                  formatter:function(value,row,index){return "<a href='"+value+"' target='_blank'>文章链接</a>";
		                  }},
		                {title: '文章类型',field: 'news_type',align: 'left',sortable:'true'},
		                {title: '评论',field: 'cmt_count',align: 'center',sortable:'true'},
		                {title: '创建时间',field: 'create_time',align: 'left',sortable:'true'},
		                {title: '爬取时间',field: 'last_update_time',align: 'left',
		                  formatter:function(value,row,index){ 
		                	  var time=new Date(value);
		                	  return value?time.format("yyyy-MM-dd hh:mm:ss"):"-";
		                  },sortable:'true'}
		                ]
	  	});
	
		// 1.初始化Table
		var oTable = new TableInit();
		oTable.Init();
		
		$("#verifycode").bind('keydown',function(event){  
		  if(event.keyCode == "13")      
		  {  
			  doLogin();
		  }  
		});
		$("#emailCode").bind('keydown',function(event){  
		  if(event.keyCode == "13")      
		  {  
			  doLoginEmail();
		  }  
		});

		var interval;
		$("div[aria-controls]").click(function(){
			if($(this).attr('aria-controls')=='chan'){
				interval=setInterval(function(){  
			        $.ajax({  
			            url : 'status',  
			            type : 'post',  
			            success : function(data){
			            	var da=eval('('+data+')');
			            	$("#spiderStatus").text(da.spiderStatus);
			            	$("#pageCount").text(da.pageCount);
			            	/* huadong('.slider-minmax1', "%", da.cpuUsage, 100);//CPU使用率
			                huadong('.slider-minmax2', "个", da.threadAlive,50);//活动线程数
			                huadong('.slider-minmax3', "M", da.memUsage, 2048);//内存使用量
			                huadong('.slider-minmax4', "M", da.freeMemory, 1024);//JVM可用内存
			                huadong('.slider-minmax5', "个", da.availableProcessors, 50);//JVM可用线程数 */
			            }  
			        });  
			    },1000);
			}else{
				window.clearInterval(interval);
				if($(this).attr('aria-controls')=='rate'){
					$.ajax({url:"getRate",type:"get",dataType:"text",success:function(data){
						$("#incomeRate1").text(data);
					}});
				}
				if($(this).attr('aria-controls')=='regu'){
					
				}
			}
		});
	
	    $(".meun-item").click(function() {
	        $(".meun-item").removeClass("meun-item-active");
	        $(this).addClass("meun-item-active");
	        var itmeObj = $(".meun-item").find("img");
	        itmeObj.each(function() {
	            var items = $(this).attr("src");
	            items = items.replace("_grey.png", ".png");
	            items = items.replace(".png", "_grey.png")
	            $(this).attr("src", items);
	        });
	        var attrObj = $(this).find("img").attr("src");
	        ;
	        attrObj = attrObj.replace("_grey.png", ".png");
	        $(this).find("img").attr("src", attrObj);
	    });
	    $("#topAD").click(function() {
	        $("#topA").toggleClass(" glyphicon-triangle-right");
	        $("#topA").toggleClass(" glyphicon-triangle-bottom");
	    });
	    $("#topBD").click(function() {
	        $("#topB").toggleClass(" glyphicon-triangle-right");
	        $("#topB").toggleClass(" glyphicon-triangle-bottom");
	    });
	    $("#topCD").click(function() {
	        $("#topC").toggleClass(" glyphicon-triangle-right");
	        $("#topC").toggleClass(" glyphicon-triangle-bottom");
	    });
	    $(".toggle-btn").click(function() {
	        $("#leftMeun").toggleClass("show");
	        $("#rightContent").toggleClass("pd0px");
	    })
	    
});
function login(){
	var sel = $("#accountTable").bootstrapTable("getSelections");
	if(sel.length > 0){
//		if(sel[0].loginCookie!=undefined && sel[0].loginCookie!=""){
//			alert("当前cookie有效，可以直接登录");
//		}else{
			$("#verifycode").val("");
			$("#name").val(sel[0].username);
			$("#codeImage").attr("src","auto/getCode?username="+sel[0].username+"&aa="+new Date());
			$('#myModal').modal("show");
//		}
	}else{
		alert("请选择一条数据");
	};
}
function sendFinish(){
	var sel = $("#accountTable").bootstrapTable("getSelections");
	if(sel.length > 0){
		$.ajax({  
            url : 'sendFinish',  
            type : 'post',  
            data:{username:sel[0].username},
            success : function(data){
            	if(data=='ok'){
            		$("#accountTable").bootstrapTable("refresh");
            	}
            }  
        });  
	}else{
		alert("请选择一条数据");
	};
}
function doLogin(){
	$.ajax({url:"auto/login",type:"post",dataType:'text',
		data:{username:$("#name").val(),verifycode:$("#verifycode").val()},
		success:function(data){
			alert(data);
			if(data=="获取cookie成功"){
				$("#myModal").modal("hide");
				$("#accountTable").bootstrapTable("refresh");
			}
			if(data=="需要邮箱验证"){
				var sel = $("#accountTable").bootstrapTable("getSelections");
				$("#nameemail").val(sel[0].username);
				$("#emailUsername").text(sel[0].username);
				$("#emialPassoord").text(sel[0].emailPassword);
				$("#mailUrl").attr("href","https://www.baidu.com/s?wd=163邮箱登陆&username="
						+sel[0].username.substring(0,sel[0].username.indexOf('@'))+"&emailpa="+sel[0].emailPassword);
				$("#myModal").modal("hide");
				$("#emailCheck").modal("show");
			}
		}
	});
}

function doLoginEmail(){
	$.ajax({url:"auto/checkEmailLogin",type:"post",dataType:'text',
		data:{username:$("#nameemail").val(),emailCode:$("#emailCode").val()},
		success:function(data){
			alert(data);
			if(data=="获取cookie成功"){
				$("#emailCheck").modal("hide");
				$("#accountTable").bootstrapTable("refresh");
			}
		}
	});
}

function chageCode(){
	var sel = $("#accountTable").bootstrapTable("getSelections");
	if(sel.length > 0){
		$("#codeImage").attr("src","auto/getCode?username="+sel[0].username+"&a="+new Date());
	}
}

function doSearch(){
	var title=encodeURI($("#title").val());
	var create_time=$("#create_time").val();
	var news_type=$("#news_type").val();
	 $("#table").bootstrapTable('refresh', {url: 
		 'list.json?title='+title+'&news_type='+news_type+'&create_time='+create_time+'&limit=10&offset=0'});
}
function doSearchAccount(){
//	$('#accountTable').bootstrapTable("refresh");
	var platform=$("#platform").val();
	var username=$("#username").val();
	var remark2=$("#remark2").val();
	 $("#accountTable").bootstrapTable('refresh', 
			 {url:'account.json?platform='+platform+'&username='+username+'&remark2='+remark2});
}
function doClear(){
	$("#title").val("");
	$("#create_time").val("");
	$("#news_type").val("");
	$("#platform").val("");
	$("#username").val("");
	$("#allotUser").val("");
	$("#remark2").val("");
}


function startSpider(){
	$.ajax({url:"start"});
}
function stopSpider(){
	$.ajax({url:"stop"});
}
function updateRate(){
	var incomRate=$("#incomeRate").val();
	$.ajax({url:"updateRate",type:"post",dataType:'text',data:{incomeRate:$("#incomeRate").val()},
		success:function(data){
			alert(data);
			$("#incomeRate1").text($("#incomeRate").val());
		}
	});
}

var TableInit = function() {
	var oTableInit = new Object();
	// 初始化Table
	oTableInit.Init = function() {
		$('#accountTable').bootstrapTable({
			url : 'account.json', // 请求后台的URL（*）
			method : 'get', // 请求方式（*）
			//toolbar : '#toolbar', // 工具按钮用哪个容器
			//striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			//queryParams : oTableInit.queryParams,// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			singleSelect: true,
			clickToSelect : true, // 是否启用点击选中行
	        columns: [{title: '',field: 'state',align: 'center',checkbox:"true"},
	                {title: '分配用户',field: 'platform',align: 'left',sortable:'true'},
	                {title: '用户名',field: 'username',align: 'left',sortable:'true',},
	                {title: '密码',field: 'password',align: 'left',sortable:'true'},
	                {title: '邮箱密码',field: 'emailPassword',align: 'center',sortable:'true'},
	                //{title: '分配用户',field: 'allotUserQuery',align: 'left',sortable:'true'},
	                {title: '验证码',field: 'loginCookie',align:'center',sortable:'true',
	                	formatter:function(value,row,index){
	                		return (value)?"<span style='color:blue;font-size:15px;'>&#10004</span>":"<span style='color:red;font-size:15px;'>&#10006</span>";
	                	}
	                },
	                {title: '发文完成',field: 'remark2',align: 'left',sortable:'true',
	                	formatter:function(value,row,index){
	                		return (value)?"完成":"-";
	                	}
	                },
	                {title: '操作',field: 'operation',align: 'left',
	                	formatter:function(value,row,index){
	                		var str="";
	                		if(row.loginCookie){
	                			str="<a href='http://ir.baidu.com/phoenix.zhtml?c=188488&p=irol-irhome&username="
	                				+row.username+"' target='_blank'>一键登录</a>&nbsp;&nbsp;&nbsp;";
	                		}else{
	                			str="请先获取验证码";
	                		}
	                		return str;
	                	},sortable:'true'},
	                ]
		});
	};

//	// 得到查询的参数
//	oTableInit.queryParams = function(params) {
//		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
//			limit : params.limit, // 页面大小
//			offset : params.offset, // 页码
//			platform : $("#platform").val(),
//			username : $("#username").val(),
//			remark2:$("#remark2").val()
//		};
//		return temp;
//	};
	return oTableInit;
};

