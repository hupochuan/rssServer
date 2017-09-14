package com.ycl.bean;

import java.util.Date;

public class RSSItemBean {
	private String title;
    private String author;
    private String uri;
    private String link;
    private String discription;
    private Date pubDate;
    private String type;
    private String content;
    private int rssid;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRssid() {
		return rssid;
	}
	public void setRssid(int rssid) {
		this.rssid = rssid;
	}
	public String toString(){
		return "title:"+this.title+" author:"+this.author+" uri:"+this.uri+" link:"+this.link+" discription:"
	+this.discription+" pubDate:"+this.pubDate+" type:"+this.type+" content:"+this.content;
	   
	}

}
