package com.ycl.bean;

public class Website {
	
	private String name;
	private String url;
	private String startTag;
	private String endTag;
	private String encoding;
	private String rssid;
	private int interval;
	private String theme;
	private String open;
	private String unified;
	private Boolean theme_chooseable;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStartTag() {
		return startTag;
	}
	public void setStartTag(String startTag) {
		this.startTag = startTag;
	}
	public String getEndTag() {
		return endTag;
	}
	public void setEndTag(String endTag) {
		this.endTag = endTag;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getRssid() {
		return rssid;
	}
	public void setRssid(String rssid) {
		this.rssid = rssid;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public Boolean getTheme_chooseable() {
		return theme_chooseable;
	}
	public void setTheme_chooseable(Boolean theme_chooseable) {
		this.theme_chooseable = theme_chooseable;
	}
	public String toString(){
		return "";
	}
	public String getUnified() {
		return unified;
	}
	public void setUnified(String unified) {
		this.unified = unified;
	}
	
}
