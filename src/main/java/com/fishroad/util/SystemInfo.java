package com.fishroad.util;

import java.io.*;

/**
 * linux 下cpu 内存 磁盘 jvm的使用监控
 * 
 * @author avery_leo
 *
 */
public class SystemInfo {

	/**
	 * 
	 * 获取cpu使用情况
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */

	public String getCpuUsage() throws Exception {
		String cpuUsed = null;
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("top -b -n 1");// 调用系统的“top"命令
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String str = null;
			while ((str = in.readLine()) != null) {
				if(str.startsWith("Cpu(s):")){
					cpuUsed=str;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return cpuUsed;
	}
	/**
	 * 内存监控
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMemUsage() throws Exception {
		String menUsed = null;
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("top -b -n 1");// 调用系统的“top"命令
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String str = null;
			while ((str = in.readLine()) != null) {
				if(str.startsWith("Men:")){
					menUsed=str;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return menUsed;
	}

	/**
	 * 获取磁盘空间大小
	 * @throws Exception
	 */

	public double getDeskUsage() throws Exception {
		double totalHD = 0;
		double usedHD = 0;
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("df -hl");// df -hl 查看硬盘空间
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String str = null;
			String[] strArray = null;
			int flag = 0;
			while ((str = in.readLine()) != null) {
				int m = 0;
				//
				if (flag > 0) {
					flag++;
					strArray = str.split("");
					for (String tmp : strArray) {
						if (tmp.trim().length() == 0)
							continue;
						++m;
						//
						System.out.println("----tmp----" + tmp);
						if (tmp.indexOf("G") != -1) {
							if (m == 2) {
								//
								System.out.println("---G----" + tmp);
								if (!tmp.equals("") && !tmp.equals("0"))
									totalHD += Double.parseDouble(tmp
											.substring(0, tmp.length() - 1)) * 1024;
							}
							if (m == 3) {
								System.out.println("---G----" + tmp);
								if (!tmp.equals("none") && !tmp.equals("0"))
									usedHD += Double.parseDouble(tmp.substring(
											0, tmp.length() - 1)) * 1024;
							}
						}
						if (tmp.indexOf("M") != -1) {

							if (m == 2) {
								System.out.println("---M---" + tmp);
								if (!tmp.equals("") && !tmp.equals("0"))
									totalHD += Double.parseDouble(tmp
											.substring(0, tmp.length() - 1));
							}
							if (m == 3) {
								System.out.println("---M---" + tmp);
								if (!tmp.equals("none") && !tmp.equals("0"))
									usedHD += Double.parseDouble(tmp.substring(
											0, tmp.length() - 1));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return (usedHD / totalHD) * 100;
	}

	public static void main(String[] args) throws Exception {
		SystemInfo cpu = new SystemInfo();
		System.out.println("---------------cpu used:" + cpu.getCpuUsage() + "%");
		System.out.println("---------------mem used:" + cpu.getMemUsage() + "%");
		System.out.println("---------------HD used:" + cpu.getDeskUsage() + "%");
		System.out.println("------------jvm监控----------------------");
		Runtime lRuntime = Runtime.getRuntime();
		System.out.println("--------------Free Momery:" + lRuntime.freeMemory() + "K");
		System.out.println("--------------Max Momery:" + lRuntime.maxMemory() + "K");
		System.out.println("--------------Total Momery:" + lRuntime.totalMemory() + "K");
		System.out.println("---------------Available Processors :"
				+ lRuntime.availableProcessors());
	}
}
