

import java.util.List;

import com.ycl.DbUtil.RssDao;
import com.ycl.Util.Dom4jUtil;
import com.ycl.Util.RssReader;
import com.ycl.bean.RSSItemBean;
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
    	 for(Website we:websiteList){
    		 if(we.getOpen().equals("true")){
    			 System.out.println("==========begin spide " + we.getName() + ".==============");
                 rssInsert(we);
                 System.out.println("==========end spide " + we.getName() + ".==============");
    			 
    		 }
    		
    	 }
       
    }
    public void rssInsert(Website we){
    	List<RSSItemBean> rssList=null;
    	
        try {
			rssList = new RssReader().getContent(we);
			 RssDao rssDao = new RssDao();
	            if (rssList != null) {
	                int size = rssList.size();
	                for (int i = 0; i < size; i++) {
	                    RSSItemBean rs = rssList.get(i);
	                    if(rs.getContent().equals("")){
	                        continue;
	                    }
	                    rssDao.insert(rs);
	                }
	            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
	

}
