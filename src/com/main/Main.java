package com.main;

import java.util.List;

import com.ycl.Util.Dom4jUtil;
import com.ycl.bean.Website;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main().init();
		

	}
	public void init(){
        start();
        spiderRun();
        end();
    }
	public void start() {
        System.out.println("==========web spider running.==============");
    }

    public void end() {
        System.out.println("=============insert complemt==============");
        System.out.println("==============task success!!.==============");
    }
    public void spiderRun() {
    	 List<Website>  websiteList = new Dom4jUtil().parserXml("website.xml");
       
    }
	

}
