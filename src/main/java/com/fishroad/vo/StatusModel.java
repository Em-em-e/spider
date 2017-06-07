package com.fishroad.vo;

public class StatusModel {
	
	
	private String spiderStatus;
	
	private long pageCount;
	
	private int threadAlive;
	private String startTime;
	
	private String cpuUsage;
	private String memUsage;
	
	private long freeMemory; 
	private long maxMemory;
	private long totalMemory;
	private int availableProcessors;
	public String getSpiderStatus() {
		return spiderStatus;
	}
	public void setSpiderStatus(String spiderStatus) {
		this.spiderStatus = spiderStatus;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public int getThreadAlive() {
		return threadAlive;
	}
	public void setThreadAlive(int threadAlive) {
		this.threadAlive = threadAlive;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public String getMemUsage() {
		return memUsage;
	}
	public void setMemUsage(String memUsage) {
		this.memUsage = memUsage;
	}
	public long getFreeMemory() {
		return freeMemory;
	}
	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}
	public long getMaxMemory() {
		return maxMemory;
	}
	public void setMaxMemory(long maxMemory) {
		this.maxMemory = maxMemory;
	}
	public long getTotalMemory() {
		return totalMemory;
	}
	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}
	public int getAvailableProcessors() {
		return availableProcessors;
	}
	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}
	
	
	
}
