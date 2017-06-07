

	//获取倍数
	$.get("http://localhost:8080/spider/getRate",function(data){
		var rate=parseFloat(data);
		
		//头条
		var a1=$(".pgc-dashboard-column .pgc-dashboard-primary").eq(0).text("dfsdf");
		console.log(a1.eq(0));

		//百度百家
		setTimeout(function(){
			//首页  昨日收益
			var a2=$(".index-top-statistics-number:eq(0)");
			console.log(a2);
			if(!isNaN(parseFloat(a2.text())))
				a2.text(a2.text()*rate);
		}, 1000);
		
		var income1=$(".num.money:eq(0)");
		var income2=$(".num.money:eq(1)");
		var income3=$(".num.money:eq(2)");
//			console.log(income1.text());
//			console.log(income2.text());
//			console.log(income3.text());
			
		//收入广场 昨日收益
		if(!isNaN(parseFloat(income1.text())))
			income1.text(parseFloat(income1.text())*rate);
		//收入广场 7日收益
		if(!isNaN(parseFloat(income2.text())))
			income2.text(parseFloat(income2.text())*rate);
		//收入广场 30日收益
		if(!isNaN(parseFloat(income3.text())))
			income3.text(parseFloat(income3.text())*rate);
		
	},"json");
	
	
	
	