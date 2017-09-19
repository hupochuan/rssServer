package com.ycl.Util;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.ycl.bean.RSSItemBean;
import com.ycl.bean.Website;

import javax.imageio.ImageIO;

import org.xml.sax.InputSource;










import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RssReader {

	public static void main(String[] args) throws IOException, IllegalArgumentException {


	}
	public List<RSSItemBean> getRss(String url) throws Exception {
		System.out.println(url);
        URL feedUrl = new URL(url);//SyndFeedInput:��Զ�̶���xml�ṹ������ת��SyndFeedImplʵ��
        URLConnection conn = feedUrl.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"); //��ƭ������
        SyndFeedInput input = new SyndFeedInput();//rome��SyndFeed��������rss��atom��ʵ��,

        SyndFeed feed = input.build(new XmlReader(conn));   //SyndFeed��rss��atomʵ����SyndFeedImpl�Ľӿ�
        //System.out.println(feed);
        List<SyndEntry> entries = feed.getEntries();
        RSSItemBean item = null;
        List<RSSItemBean> rssItemBeans = new ArrayList<RSSItemBean>();
        for (SyndEntry entry : entries) {
            item = new RSSItemBean();
            item.setTitle(entry.getTitle().trim());
            item.setType(feed.getTitleEx().getValue().trim());
            item.setUri(entry.getUri());
            item.setPubDate(entry.getPublishedDate());
            item.setAuthor(entry.getAuthor());
            rssItemBeans.add(item);
    
        }
       
        return rssItemBeans;
    }
	
	public List<RSSItemBean> getContent(Website website) throws Exception {
        String content;
       
        List<RSSItemBean> rssList = getRss(website.getUrl());
       
       
        for (RSSItemBean rsItem : rssList) {
       
        	if(website.getUnified().equals("false")){
        		
        		 if(Constants.websitecontent.get(rsItem.getAuthor()) != null){
        			 	
        			 String[] tags=Constants.websitecontent.get(rsItem.getAuthor()).split(",");
      
        			 FindHtmlContent findHtml = new FindHtmlContent(tags[0], tags[1], tags[2]);
            		 String link = rsItem.getUri();
                     content = findHtml.getContent(link);   //�ؼ���������ȡ��������
                     //content = processImages(content);          //ת��ͼƬ
                     rsItem.setContent(content);
 
                     rsItem.setRssid(Integer.parseInt(website.getRssid()));
                     rsItem.setRssname(website.getName());
        			 
                     System.out.println(rsItem.toString());
        		 }
        		 
        	}else{
        		 FindHtmlContent findHtml = new FindHtmlContent(website.getStartTag(),website.getEndTag(), website.getEncoding());
        		 String link = rsItem.getUri();
                 content = findHtml.getContent(link);   //�ؼ���������ȡ��������
                 //content = processImages(content);          //ת��ͼƬ
                 rsItem.setContent(content);

                 rsItem.setRssid(Integer.parseInt(website.getRssid()));
                 rsItem.setRssname(website.getName());
                 
                 System.out.println(rsItem.toString());
    			 
        		
        	}
        }
        return rssList;
    }

}
