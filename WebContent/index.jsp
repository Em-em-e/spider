<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="ch">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <title>网易新闻数据爬虫系统</title>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(function() {
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
            })
        </script>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/common.css" />
        <link rel="stylesheet" type="text/css" href="css/slide.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="css/flat-ui.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.nouislider.css">
        
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<link href="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.css" rel="stylesheet"/> 
		<script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>
		<script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    </head>
    <body>
        <div id="wrap">
            <!-- 左侧菜单栏目块 -->
            <div class="leftMeun" id="leftMeun">
                <div id="logoDiv">
                    <p id="logoP"><img id="logo" alt="网易新闻数据爬虫系统" src="images/logo.png"><span>网易文章采集系统</span></p>
                </div>
                <div id="personInfor">
                    <p id="userName">李亮</p>
                    <p><span>endtcy_ll@163.com</span> <a href="http://endtcy.github.io/" target="_blank"> GitHub</a></p>
                    <p>
                        <a>退出登录</a>
                    </p>
                </div>
                <div class="meun-title">数据管理</div>
                <div class="meun-item meun-item-active" href="#user" aria-controls="user" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">所有文章</div>
                <div class="meun-item" href="#chan" aria-controls="chan" role="tab" data-toggle="tab"><img src="images/icon_change_grey.png">爬虫管理</div>
                <div class="meun-title">用户管理</div>
                <div class="meun-item" href="#scho" aria-controls="scho" role="tab" data-toggle="tab"><img src="images/icon_house_grey.png">所有用户</div>
                <div class="meun-item" href="#regu" aria-controls="regu" role="tab" data-toggle="tab"><img src="images/icon_rule_grey.png">批量登录</div>
                <div class="meun-item" href="#stud" aria-controls="stud" role="tab" data-toggle="tab"><img src="images/icon_card_grey.png">修改密码</div>
                <div class="meun-item" href="#sitt" aria-controls="sitt" role="tab" data-toggle="tab"><img src="images/icon_char_grey.png">批量导入</div>
            </div>
            <!-- 右侧具体内容栏目 -->
            <div id="rightContent">
                <a class="toggle-btn" id="nimei">
                    <i class="glyphicon glyphicon-align-justify"></i>
                </a>
                <!-- Tab panes -->
                <div class="tab-content">
                    
            <!--所有文章模块-->
            <div role="tabpanel" class="tab-pane active" id="user">
                <div class="check-div form-inline">
                    <div class="col-xs-3">
                        	标题：<input type="text" id="title" name="title" class="form-control input-sm" placeholder="搜索标题">
                        	
                    </div>
                    <div class="col-xs-4">
                   		 文章类型：<input type="text" id="news_type" name="news_type" class="form-control input-sm" placeholder="类型代码">
                    </div>
                    <div class="col-xs-5">
                        	创建时间：<input type="text" id="create_time" name="create_time" class="form-control input-sm" placeholder="注意日期格式">
                        <button class="btn btn-white btn-xs " onclick="doSearch()">查 询 </button>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-2 ">
                        </div>
                        <div class="col-xs-2">
                        </div>
                        <div class="col-xs-2">
                        </div>
                        <div class="col-xs-2">
                        </div>
                        <div class="col-xs-2">
                        </div>
                        <div class="col-xs-2">
                        </div>
                    </div>
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="span12">
								<table id="table" style="overflow:hidden;white-space:nowrap; ">
								</table>
								<br>
							</div>
						</div>
					</div>
	</div>
                <!-- /.modal -->
            </div>
            <!--爬虫管理模块-->
            <div role="tabpanel" class="tab-pane" id="chan" style="padding-top: 50px;">
                <div class="data-div">
                    <div class="tablebody col-lg-10 col-lg-offset-1">
                    	<div class="row">
                    		<div class="col-xs-3" style="padding-right: 0;"><h6>爬虫状态：<span id="spiderStatus"></span></h6></div>
                            <div class="col-xs-3">
                            	<h6>解析页面数：<label id="pageCount"></label></h6>
                            </div>
                            <div class="col-xs-2">
                            	<button class="btn btn-success btn-xs" data-toggle="modal" data-target="#changeTime"><a onclick="startSpider()">启动</a></button>&nbsp;&nbsp;
                            	<button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#deleteTime"><a onclick="stopSpider()">停止</a></button>
                            </div>
                    	</div>
                        <div class="row">
                            <div class="col-xs-3" style="padding-right: 0;">CPU使用率</div>
                            <div class="col-xs-7 expand-col">
                                <div class="slider-minmax1">
                                </div>
                                <div class="row top100">
                                    <span class="left">0</span>
                                    <span class="right">100</span>
                                </div>
                            </div>
                            <div class="col-xs-2">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-3" style="padding-right: 0;">活动线程数</div>
                            <div class="col-xs-7 expand-col">
                                <div class="slider-minmax2">
                                </div>
                                <div class="row top100">
                                    <span class="left">0</span>
                                    <span class="right">50</span>
                                </div>
                            </div>
                            <div class="col-xs-2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-3" style="padding-right: 0;">内存使用量</div>
                            <div class="col-xs-7 expand-col">
                                <div class="slider-minmax3">
                                </div>
                                <div class="row top100">
                                    <span class="left">0</span>
                                    <span class="right">2048</span>
                                </div>
                            </div>
                            <div class="col-xs-2">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-3" style="padding-right: 0;">JVM可用内存</div>
                            <div class="col-xs-7 expand-col">
                                <div class="slider-minmax4">
                                </div>
                                <div class="row top100">
                                    <span class="left">0</span>
                                    <span class="right">100</span>
                                </div>
                            </div>
                            <div class="col-xs-2">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-3" style="padding-right: 0;">JVM可用线程数</div>
                            <div class="col-xs-7 expand-col">
                                <div class="slider-minmax5">
                                </div>
                                <div class="row top100">
                                    <span class="left">0</span>
                                    <span class="right">100</span>
                                </div>
                            </div>
                            <div class="col-xs-2">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.nouislider.js"></script>

<script type="text/javascript">
$().ready(function(){
	var $table = $('#table');
	$table.bootstrapTable({
	url: "list.json", 
	dataType: "json",
	pagination: true, //分页
	singleSelect: false,
	sidePagination: "server", //服务端处理分页
	      columns: [{
	                title: '',
	                  field: 'state',
	                  align: 'center',
	                  checkbox:"true"
	              },{
	                  title: '标题',
	                  field: 'title',
	                  align: 'left',
	                  sortable:'true'
	              },{
	                  title: 'URL',
	                  field: 'url',
	                  align: 'left',
	                  sortable:'true',
	                  formatter:function(value,row,index){ 
	                	  return "<a href='"+value+"' target='_blank'>文章链接</a>";
	                  }
	              },{
	                  title: '文章类型',
	                  field: 'news_type',
	                  align: 'left',
	                  sortable:'true'
	              },{
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
	              },{
	                  title: '爬取时间',
	                  field: 'last_update_time',
	                  align: 'left',
	                  formatter:function(value,row,index){ 
	                	  var time=new Date(value);
	                	  return time.format("yyyy-MM-dd hh:mm:ss");
	                  },
	                  sortable:'true'
	              }
	          ]
	  	});
		setInterval(function(){  
	        $.ajax({  
	            url : '${pageContext.request.contextPath}/status',  
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
});

function doSearch(){
	var title=encodeURI($("#title").val());
	var create_time=$("#create_time").val();
	var news_type=$("#news_type").val();
	var create_time=$("#create_time").val();
	 $("#table").bootstrapTable('refresh', {url: 
		 'list.json?title='+title+'&news_type='+news_type+'&create_time='+create_time+'&limit=10&offset=0'});
}

function startSpider(){
	$.ajax({url:"start"});
}
function stopSpider(){
	$.ajax({url:"stop"});
}
</script>
<script>
      //min/max slider
      function huadong(my, unit, def, max) {
          $(my).noUiSlider({
              range: [0, max],
              start: [def],
              handles: 1,
              connect: 'upper',
              slide: function() {
                  var val = Math.floor($(this).val());
                  $(this).find(".noUi-handle").text(
                          val + unit
                          );
              },
              set: function() {
                  var val = Math.floor($(this).val());
                  $(this).find(".noUi-handle").text(
                          val + unit
                          );
              }
          });
          $(my).val(def, true);
      }
      huadong('.slider-minmax1', "%", "86", 100);
      huadong('.slider-minmax2', "个", "6", 50);
      huadong('.slider-minmax3', "M", "1006", 2048);
      huadong('.slider-minmax4', "M", "496", 1024);
      huadong('.slider-minmax5', "个", "14", 50);
      
      function setHuadong(my,def){
    	  $(my).noUiSlider({
              start: [def],
          });
          $(my).val(def, true);
      }
</script>
</body>

</html>