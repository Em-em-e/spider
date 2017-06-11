package com.fishroad.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BaiduTest {
	
	private String cookie;

	public String getGCookie(String url) {
		String cok = "";
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoInput(true);
			conn.setDoInput(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
			List<String> ckm = conn.getHeaderFields().get("Set-Cookie");
			String cookie = "";
			for (int i = 0; i < ckm.size(); i++) {
				cookie += ckm.get(i) + ";";
			}
			cok = cookie;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cok;
	}

	public String getToken(String cookie) {
		String token = "";
		try {
			String url = "https://passport.baidu.com/v2/api/?getapi&class=login&tpl=mn&tangram=true";
						  
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoInput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Cookie", cookie);
			conn.setRequestProperty("Host", "passport.baidu.com");
			conn.setRequestProperty("Referer", "http://zhidao.baidu.com/html/userlogin.html?t=1337825648752");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine();
			while ((s = br.readLine()) != null) {
	            if (s.contains("login_token")) {
	                token = s.substring(s.indexOf("'") + 1,
	                        s.lastIndexOf("'"));
	            }
	        }
//			while (s != null) {
//				if (s.indexOf("bdPass.api.para***ogin_token") != -1) {
//					System.out.println(s);
//					token = s.replaceAll(".*'(.*?)'.*", "$1");
//					break;
//				}
//				s = br.readLine();
//			}
			br.close();
			isr.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	public String login(String name, String pass) throws Exception {
		String ck = getGCookie("http://zhidao.baidu.com/html/userlogin.html?t=1337825648742");
		String tok = getToken(ck);// 获取token，需要设置一下当前cookie
		System.out.println(tok);
		String login = "password=##password##&index=0&staticpage=http://zhidao.baidu.com/&token=" + tok
				+ "&verifycode=&isPhone=false&charset=GB2312&mem_pass=on&tpl=ik&callback=parent.bdPass.api.login._postCallback&u=http://zhidao.baidu.com/q?ct=17&pn=0&tn=ikaslist&rn=10&word=&username=##username##&safe***=0";
		login = login.replace("##password##", pass);
		login = login.replace("##username##", name);
		String u = "https://passport.baidu.com/?login";
		URL url = new URL(u);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
		OutputStream ops = conn.getOutputStream();
		ops.write(login.getBytes());
		ops.close();
		List<String> ckm = conn.getHeaderFields().get("Set-Cookie");
		String cookie = "";
		for (int i = 0; i < ckm.size(); i++) {
			cookie += ckm.get(i) + ";";
		}
		System.out.println(cookie);
		this.cookie = cookie;
		return cookie;
	}

	public boolean islogin(String cookie) {
		boolean login = false;
		try {
			String u = "http://zhidao.baidu.com/q?ct=28&lm=1";
			URL url = new URL(u);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Cookie", cookie);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine().trim();
			if (s.trim().equals("true")) {
				login = true;
			}
			br.close();
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(login);
		return login;
	}
	
	
	public static void main(String[] args) {
		BaiduTest b=new BaiduTest();
		try {
			b.login("18321830773", "ll920521609219");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(b.islogin(b.cookie));
	}

}
