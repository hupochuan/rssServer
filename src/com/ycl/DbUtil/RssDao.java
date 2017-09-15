package com.ycl.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.ycl.bean.RSSItemBean;



public class RssDao {
	public void insert(RSSItemBean rsItem) {
	    Connection con = null;
	    con = DbUtil.getSqlConnection();
	    if (!checkExist(rsItem.getTitle())) {                //检查内容是否已经被抓取了
          
            PreparedStatement st;
            try {
                DbUtil.beginTransaction(con);  //开启事物
                //values(15,36,13,1,'狂飙蜗牛',3,'aa',1383553857,'bb','1270.0.1',2998,1,-1,0,1);
                String sql = "INSERT INTO rss_article(title,author,uri,pubDate,type,content,rssid,created_time) values(?,?,?,?,?,?,?,?)";
                st = (PreparedStatement) con.prepareStatement(sql);    // 创建用于执行静态sql语句的Statement对象
                st.setString(1, rsItem.getTitle());
                st.setString(2, rsItem.getAuthor());
                st.setString(3, rsItem.getUri());
                if(rsItem.getPubDate()!=null){
                	st.setTimestamp(4,new java.sql.Timestamp(rsItem.getPubDate().getTime()));
                }else{
                	st.setTimestamp(4,new java.sql.Timestamp(new java.util.Date().getTime()));
                }
                
                st.setString(5,rsItem.getType());
                st.setString(6,rsItem.getContent());
                st.setInt(7,rsItem.getRssid());
                st.setTimestamp(8,new java.sql.Timestamp(new java.util.Date().getTime()));
                
                
                st.executeUpdate();


    
                DbUtil.commitTransaction(con);

            } catch (Exception e) {
                e.printStackTrace();
                DbUtil.rollBackTransaction(con);
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println("success add subject：" + rsItem.getTitle());
        } else {
            System.out.println("this subject is exist ：" + rsItem.getTitle());
        }
	}
	public Boolean checkExist(String title) {
        String sql = "select * from rss_article p where p.title = ?";
        Connection conn = DbUtil.getSqlConnection();
        try {
            PreparedStatement prest = conn.prepareStatement(sql);
            prest.setString(1, title);
            ResultSet rs = prest.executeQuery();
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
}
