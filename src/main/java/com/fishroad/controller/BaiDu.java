package com.fishroad.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fishroad.util.RSAUtil;

public class BaiDu {
	HttpClient client;
    @SuppressWarnings("serial")
	Map<String, String> context = new HashMap<String, String>() {
		@Override
		public String put(String key, String value) {
            System.out.println(key + ":" + value);
            return super.put(key, value);
        };
    };
    public CookieStore cookieStore;
    HttpGet get;
    HttpPost post;
    HttpResponse res;
    public String errorMsg;
    public String respString;

    public BaiDu(String username,String password) {
        try {
        	context.put("username", username);
            context.put("pass", password);
            cookieStore = new BasicCookieStore();
            List<Header> headers = new ArrayList<Header>();
            headers.add(new BasicHeader("user-agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36"));
            client = HttpClients.custom().setDefaultCookieStore(cookieStore)
                    .setDefaultHeaders(headers).build();
            get = new HttpGet();
            res = null;
            get.setURI(new URI("http://pan.baidu.com/"));
            client.execute(get);
            getToken();
            Encrypt();
        } catch (Exception e) {
            this.errorMsg="初始化失败！";
            e.printStackTrace();
        }
    }public BaiDu(String username,String password,CookieStore c) {
        try {
        	context.put("username", username);
            context.put("pass", password);
            this.cookieStore = c;
            List<Header> headers = new ArrayList<Header>();
            headers.add(new BasicHeader("user-agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36"));
            client = HttpClients.custom().setDefaultCookieStore(cookieStore)
                    .setDefaultHeaders(headers).build();
            get = new HttpGet();
            res = null;
            get.setURI(new URI("http://pan.baidu.com/"));
            client.execute(get);
            getToken();
            Encrypt();
        } catch (Exception e) {
            this.errorMsg="初始化失败！";
            e.printStackTrace();
        }
    }
    
    

	public void errorhandle() throws Exception {
        if (context.get("error") == null) {
            errorMsg="登录成功";
            return;
        }
        switch (Integer.valueOf(context.get("error"))) {
        case 257:
            errorMsg="257";
            break;
        case 120021://需要身份验证，发送邮件验证码
        	sendEmailCode();
        	break;
        case 18:
        	errorMsg="该账号未绑定邮箱，请先绑定邮箱";
        	break;
        case 0:
        	errorMsg="获取cookie成功";
            break;
        default:
        	errorMsg="登录失败，请再试一次";
            break;
        }
    }
	
    public void login() throws Exception {
        @SuppressWarnings("serial")
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>() {
            @Override
            public boolean add(BasicNameValuePair e) {
                if (e.getValue() == null) {
                    e = new BasicNameValuePair(e.getName(), "");
                }
                return super.add(e);
            }
        };
        params.add(new BasicNameValuePair("staticpage",
                "https://tieba.baidu.com/index.html"));
        params.add(new BasicNameValuePair("charset", "utf-8"));
        params.add(new BasicNameValuePair("apiver", "v3"));
        params.add(new BasicNameValuePair("token", context.get("token")));
        params.add(new BasicNameValuePair("tpl", "mn"));
        params.add(new BasicNameValuePair("tt", System.currentTimeMillis() + ""));
        params.add(new BasicNameValuePair("safeflg", String.valueOf(0)));
        params.add(new BasicNameValuePair("u", "http://baijiahao.baidu.com/"));
        params.add(new BasicNameValuePair("detect", "1"));
        params.add(new BasicNameValuePair("gid", ""));
        params.add(new BasicNameValuePair("quick_user", "0"));
        params.add(new BasicNameValuePair("logintype", "basicLogin"));
        params.add(new BasicNameValuePair("logLoginType", "pc_loginBasic"));
        params.add(new BasicNameValuePair("idc", ""));
        params.add(new BasicNameValuePair("loginmerge", "true"));
        params.add(new BasicNameValuePair("rsakey", context.get("rsakey")));
        params.add(new BasicNameValuePair("username", context.get("username")));
        params.add(new BasicNameValuePair("password", context.get("pass")));
        params.add(new BasicNameValuePair("isPhone", "false"));
        params.add(new BasicNameValuePair("loginType", "1"));
        params.add(new BasicNameValuePair("index", "0"));
        params.add(new BasicNameValuePair("verifycode", context
                .get("verifycode")));
        params.add(new BasicNameValuePair("codestring", context
                .get("codeString")));
        params.add(new BasicNameValuePair("mem_pass", "on"));
        params.add(new BasicNameValuePair("callback",
                "parent.bdPass.api.login._postCallback"));
        params.add(new BasicNameValuePair("crypttype", "12"));
        post = new HttpPost();
        post.setURI(new URI("https://passport.baidu.com/v2/api/?login"));
        post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        res = client.execute(post);
        String string = EntityUtils.toString(res.getEntity());
        Matcher matcher = Pattern.compile("err_no=(\\d+)&").matcher(string);
        if (matcher.find()) {
            context.put("error", matcher.group(1));
        } else {
            matcher = Pattern.compile("error=(\\d+)'").matcher(string);
            if (matcher.find()) {
                context.put("error", matcher.group(1));
            }
        }
        Matcher codem = Pattern.compile("code[sS]tring=(\\w+)&")
                .matcher(string);
        if (codem.find()) {
            context.put("codeString", codem.group(1));
        }
        
//        Matcher authtoken = Pattern.compile("authtoken=.+&")
//                .matcher(string);
//        if (authtoken.find()) {
        String auth=string.substring(string.indexOf("authtoken=")+10, string.indexOf("&", string.indexOf("authtoken=")));
        if(auth.length()>0)
            context.put("authtoken", string.substring(string.indexOf("authtoken=")+10, string.indexOf("&", string.indexOf("authtoken="))));
//        }
        errorhandle();
    }
    
    public boolean checkCookie() throws Exception{
    	get.setURI(new URI("http://baijiahao.baidu.com/"));
		res = client.execute(get);
		String respCont=EntityUtils.toString(res.getEntity());
		
		String title=respCont.substring(respCont.indexOf("<title>"),respCont.indexOf("</title>"));
		System.out.println(title);
		if(title.indexOf("我的首页-百家号")>0){
			return true;
		}else{
			System.out.println(title);
			return false;
		}
    }
    public void checkEmailCode(String vcode) throws Exception{
    	get.setURI(new URI("https://passport.baidu.com/v2/sapi/authwidgetverify?authtoken="+context.get("authtoken")
		+ "&type=email&jsonp=1&apiver=v3&action=check&vcode="+vcode));
		res = client.execute(get);
		String respCont=EntityUtils.toString(res.getEntity());
		System.out.println(respCont);
		
		Matcher m = Pattern.compile("no\":\'(\\d+)\'").matcher(respCont);
		Matcher e = Pattern.compile("msg\":\'(.*)\'").matcher(respCont);
		if (m.find()) {
		    switch (Integer.valueOf(m.group(1))) {
		    case 62004:
		    	System.out.println("验证码错误");
		    	break;
		    case 110000:
		    	System.out.println("邮箱验证成功");
		    	errorMsg="邮箱验证成功";
		    	break;
		    default:
		    	if(e.find())
		    		System.out.println(e.group());
		        break;
		    }
		}
    }
    /***
     * 发送邮件验证码
     * @throws Exception
     */
    public void sendEmailCode() throws Exception{
    	get.setURI(new URI("https://passport.baidu.com/v2/sapi/authwidgetverify?authtoken="+context.get("authtoken")
    			+ "&type=email&jsonp=1&apiver=v3&action=send"));
        res = client.execute(get);
        String respCont=EntityUtils.toString(res.getEntity());
        System.out.println(respCont);
        
        Matcher m = Pattern.compile("no\":\'(\\d+)\'").matcher(respCont);
        Matcher e = Pattern.compile("msg\":\'(.*)\'").matcher(respCont);
        if (m.find()) {
            switch (Integer.valueOf(m.group(1))) {
            case 110000:
                System.out.println("发送成功");
                errorMsg="需要邮箱验证";
                break;
            case 110002:
            	if(e.find())
            		System.out.println(e.group(1));
            	break;
            default:
                break;
            }
        }
    }

    public void volidate(String verifycode) throws Exception {
        context.put("verifycode", verifycode);
        get.setURI(new URI("https://passport.baidu.com/v2/?checkvcode&token="
                + context.get("token") + "&tpl=mn&apiver=v3&tt="
                + System.currentTimeMillis() + "&verifycode="
                + context.get("verifycode") + "&codestring="
                + context.get("codeString")));
        res = client.execute(get);
        Matcher m = Pattern.compile("no\": \"(\\d+)\"").matcher(
                EntityUtils.toString(res.getEntity()));
        if (m.find()) {
            switch (Integer.valueOf(m.group(1))) {
            case 500002:
            	this.errorMsg="验证码错误";
                break;
            case 0:
                System.out.println("验证成功");
                login();
                break;
            default:
                break;
            }
        }
    }
    public void getCode(OutputStream os) throws Exception{
		get.setURI(new URI("https://passport.baidu.com/cgi-bin/genimage?"
                + context.get("codeString")));
        res = client.execute(get);
//        File file = new File("C:/Users/Administrator/Desktop/baidu/" + context.get("username") + ".gif");
//        FileUtils.touch(file);
//        OutputStream os = new FileOutputStream(file);
        res.getEntity().writeTo(os);
	}
    public String getToken() throws Exception {
        get.setURI(new URI(
                "https://passport.baidu.com/v2/api/?getapi&class=login&tpl=mn&tangram=true"));
        res = client.execute(get);
        BufferedReader br = new BufferedReader(new InputStreamReader(res
                .getEntity().getContent()));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.contains("login_token")) {
                String token = line.substring(line.indexOf("'") + 1,
                        line.lastIndexOf("'"));
                context.put("token", token);
                return context.get("token");
            }
        }
        return null;
    }
    public void Encrypt() throws Exception {
        get = new HttpGet();
        get.setURI(new URI("https://passport.baidu.com/v2/getpublickey?token="
                + context.get("token") + "&tpl=mn&apiver=v3&tt="
                + System.currentTimeMillis()));
        res = client.execute(get);
        String string = EntityUtils.toString(res.getEntity());
        Matcher matcher = Pattern.compile(
                "-----BEGIN PUBLIC KEY-----(.*)-----END PUBLIC KEY-----")
                .matcher(string);
        if (matcher.find()) {
            String s = matcher.group(1);
            context.put("pass", RSAUtil.encrypt(
                    s.replace("\\n", "").replace("\\/", "/"),
                    context.get("pass")));
        }
        Matcher matcher2 = Pattern.compile("\"key\":'(.+)'}$").matcher(string);
        if (matcher2.find()) {
            context.put("rsakey", matcher2.group(1));
        }
    }
}