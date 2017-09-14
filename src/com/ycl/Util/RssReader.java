package com.ycl.Util;


import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.ycl.bean.RSSItemBean;
import com.ycl.bean.Website;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RssReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public List<RSSItemBean> getRss(String url) throws Exception {
		System.out.println(url);
        URL feedUrl = new URL(url);//SyndFeedInput:从远程读到xml结构的内容转成SyndFeedImpl实例
        URLConnection conn = feedUrl.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"); //欺骗服务器
        SyndFeedInput input = new SyndFeedInput();//rome按SyndFeed类型生成rss和atom的实例,

        SyndFeed feed = input.build(new XmlReader(conn));   //SyndFeed是rss和atom实现类SyndFeedImpl的接口
        List<SyndEntryImpl> entries = feed.getEntries();
        RSSItemBean item = null;
        List<RSSItemBean> rssItemBeans = new ArrayList<RSSItemBean>();
        for (SyndEntryImpl entry : entries) {
            item = new RSSItemBean();
            item.setTitle(entry.getTitle().trim());
            item.setType(feed.getTitleEx().getValue().trim());
            item.setUri(entry.getUri());
            item.setPubDate(entry.getPublishedDate());
            item.setAuthor(entry.getAuthor());
            rssItemBeans.add(item);
            //System.out.println(item.toString());
        }
       
        return rssItemBeans;
    }
	
	public List<RSSItemBean> getContent(Website website) throws Exception {
        String content;
        System.out.println(website.getUrl());
        List<RSSItemBean> rssList = getRss(website.getUrl());
        FindHtmlContent findHtml = new FindHtmlContent(website.getStartTag(), website.getEndTag(), website.getEncoding());
        for (RSSItemBean rsItem : rssList) {
            String link = rsItem.getUri();

            content = findHtml.getContent(link);   //关键方法，获取新闻征文
            //content = processImages(content);          //转换图片
            rsItem.setContent(content);
            //break;
            rsItem.setRssid(Integer.parseInt(website.getRssid()));
            System.out.println(rsItem.toString());
        }
        return rssList;
    }

}
