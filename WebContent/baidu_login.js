//setTimeout(function(){
	var evaluator = new XPathEvaluator(); 
	//得到第一个div 
//	var result = evaluator.evaluate("//*[@id=\"mid\"]/div[2]/form/input[1]", document.documentElement, null, 
//	         XPathResult.FIRST_ORDERED_NODE_TYPE, null); 
//	
//	var yo=evaluator.evaluate("//*[@id=\"mid\"]/div[2]/form/label[1]", document.documentElement, null, 
//		         XPathResult.FIRST_ORDERED_NODE_TYPE, null); 
	var a1=$(".pgc-dashboard-column .pgc-dashboard-primary").eq(0).text("dfsdf");
	console.log(a1.eq(0));
	$.get("http://localhost:8080/test01/user/list",function(data){
		console.log(data);
//		$(".pgc-dashboard-column .pgc-dashboard-primary").eq(0).text(data);
	},"json");
//},3000);



//根据指定的XPATH表达式查找满足条件的所有节点
//@param xmldoc 执行查找的节点
//@param sXpath xpath的表达式
function selectNodes(xmldoc,sXpath){  
 
  if(window.ActiveXObject){       
      //IE浏览器     
      return xmldoc.selectNodes(sXpath);        
  }else if(window.XPathEvaluator){      
      //FireFox类浏览器       
      var xpathObj=new XPathEvaluator();  

      if(xpathObj){  
               var result=xpathObj.evaluate(sXpath,xmldoc,null,XPathResult.ORDERED_NODE_ITEARTOR_TYPE,null);        
          var nodes=new Array();        
          var node;        
          while((node = result.iterateNext())!=null) {        
              nodes.push(node);       
          }        
         return nodes;  
 
      }else{  
 
          return null;                              
      }  
 
  }else{        
      return null;        
  }        
}  

//根据指定的XPATH表达式查找满足条件的第一个节点
//@param xmldoc 执行查找的节点
//@param sXpath xpath的表达式
function selectSingleNode(xmldoc,sXpath){  
  if(window.ActiveXObject){       
      //IE浏览器        
      return xmldoc.selectSingleNode(sXpath);        
  }else if(window.XPathEvaluator){        
      //FireFox类浏览器        
      var xpathObj=new XPathEvaluator();        
      if(xpathObj){        
          var result=xpathObj.evaluate(sXpath,xmldoc,null,XPathResult.ORDERED_NODE_ITEARTOR_TYPE,null);                              
          return result.singleNodeValue;        
      }else{        
          return null;                               
      }        
  }else{        
      return null;        
  }        
}  