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
		                	  return time.format("yyyy-MM-dd hh:mm:ss");
		                  },sortable:'true'}
		                ]
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
						console.log(data);
						$("#incomeRate1").text(data);
					}});
				}
				if($(this).attr('aria-controls')=='regu'){
					$("#accountTable").bootstrapTable({
						url: "account.json", 
						dataType: "json",
						pagination: true, //分页
						singleSelect: false,
						sidePagination: "server", //服务端处理分页
						      columns: [{title: '',field: 'state',align: 'center',checkbox:"true"},
						                {title: '平台',field: 'platform',align: 'left',sortable:'true'},
						                {title: '用户名',field: 'username',align: 'left',sortable:'true',},
						                {title: '密码',field: 'password',align: 'left',sortable:'true'},
						                {title: '邮箱密码',field: 'emailPassword',align: 'center',sortable:'true'},
						                {title: '分配用户',field: 'allotUserQuery',align: 'left',sortable:'true'},
						                {title: 'Cookie',field: 'isActive',align: 'left',sortable:'true'},
						                {title: '操作',field: 'operation',align: 'left',
						                	formatter:function(value,row,index){
						                		console.log(row.username);
						                		var str="";
						                		str="<a href='http://ir.baidu.com/phoenix.zhtml?c=188488&p=irol-irhome&username="
						                			+row.username+"' target='_blank'>一键登录</a>&nbsp;&nbsp;&nbsp;";
						                		str+="<a href='auto/login?username="+row.username+"'>获取cookie</a>";
						                		return str;
						                	},sortable:'true'},
						                {title: '最后登录时间',field: 'lastLoginTime',align: 'left',
						                  formatter:function(value,row,index){
						                	  if(value){
						                		  var time=new Date(value);
						                		  return time.format("yyyy-MM-dd hh:mm:ss");
						                	  }else
						                		  return "-";
						                  },sortable:'true'}
						                ]
					  	});
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


function doSearch(){
	var title=encodeURI($("#title").val());
	var create_time=$("#create_time").val();
	var news_type=$("#news_type").val();
	 $("#table").bootstrapTable('refresh', {url: 
		 'list.json?title='+title+'&news_type='+news_type+'&create_time='+create_time+'&limit=10&offset=0'});
}
function doSearchAccount(){
	var platform=$("#platform").val();
	var username=$("#username").val();
	var allotUser=$("#allotUser").val();
	 $("#accountTable").bootstrapTable('refresh', {url: 
		 'account.json?platform='+platform+'&username='+username+'&allotUserQuery='+allotUser+'&limit=10&offset=0'});
}
function doClear(){
	$("#title").val("");
	$("#create_time").val("");
	$("#news_type").val("");
	$("#platform").val("");
	$("#username").val("");
	$("#allotUser").val("");
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
