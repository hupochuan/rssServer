package com.ycl.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 13-10-31
 * Time: ����12:34
 * To change this template use File | Settings | File Templates.
 */
public class FindHtmlContent {
    private String startTag;
    private String endTag;
    static String url = "http://www.ithome.com/html/it/57338.htm";
    private String pageEncoding;

    public FindHtmlContent(String startTag, String endTag, String pageEncoding) {
        this.startTag = startTag;
        this.endTag = endTag;
        this.pageEncoding = pageEncoding;
    }

    /**
     * http �����ȡ��ҳ���Դ��
     *
     * @param surl ����url
     * @return ҳ��Դ��
     */
    public String getStaticPage(String surl) {
        String htmlContent = "";
        try {
            java.io.InputStream inputStream;
            java.net.URL url = new java.net.URL(surl);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"); //��ƭ������
            connection.connect();
            inputStream = connection.getInputStream();
            byte bytes[] = new byte[1024 * 4000];
            int index = 0;
            int count = inputStream.read(bytes, index, 1024 * 4000);
            while (count != -1) {
                index += count;
                count = inputStream.read(bytes, index, 1);
            }
            htmlContent = new String(bytes, pageEncoding);
            connection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return htmlContent.trim();
    }


    /**
     * ����url ��ȡ ��������
     *
     * @param url ָ�����ĵ�url��ַ
     * @return ����Դ��
     */
    public String getContent(String url) {
        String src = getStaticPage(url);
        int startIndex = src.indexOf(startTag);   //��ʼ��ǩ
        int endIndex = src.indexOf(endTag);        //������ǩ
        //System.out.println(src);
        //System.out.println(startTag+"\t"+endTag);
        //System.out.println(startIndex+"\t"+endIndex);
        if (startIndex != -1 && endIndex != -1) {
            return src.substring(startIndex, endIndex);
        }
        return "";
    }

    public void saveFile(String filePath, String content) {
        File file = new File(filePath);
        FileWriter resultFile = null;
        try {
            resultFile = new FileWriter(file);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println(content);
            myFile.close();
            resultFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getPageEncoding() {
        return pageEncoding;
    }

    public void setPageEncoding(String pageEncoding) {
        this.pageEncoding = pageEncoding;
    }
}
