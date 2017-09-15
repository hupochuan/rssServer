package com.ycl.Util;

import java.util.Date;

import com.sun.syndication.feed.synd.SyndFeedImpl;

public class CustomSyndFeed extends SyndFeedImpl{
	 protected Date pubDate;
	 @Override
	    public Date getPublishedDate() {
	        return pubDate;
	    }

	    @Override
	    public void setPublishedDate(final Date pubDate) {
	        this.pubDate = new Date(pubDate.getTime());
	    }
	

}
