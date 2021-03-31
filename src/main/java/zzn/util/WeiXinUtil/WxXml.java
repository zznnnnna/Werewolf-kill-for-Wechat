package zzn.util.WeiXinUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import zzn.ExceptionHandler.Zzn_Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Map;

@Component
public class WxXml {

    public  static  final Logger  log= LoggerFactory.getLogger(WxXml.class);

    //获取xml
    public String getXml(HttpServletRequest request){

        String  xml="";
        String  str="";
        try {
            BufferedReader br=request.getReader();
            while ((str=br.readLine())!=null){
                xml+=str;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
      return  xml;

    }
    //解析xml
    public String[] deXml(String xmltext){

        try {
            String[] result = new String[7];
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xmltext);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
             /*NodeList nodelist1 =root.getElementsByTagName("ToUserName"); */
            result[0] = "0";
            result[1] = root.getElementsByTagName("ToUserName").item(0).getTextContent();
            result[2] = root.getElementsByTagName("FromUserName").item(0).getTextContent();
            result[3] = root.getElementsByTagName("CreateTime").item(0).getTextContent();
            result[4] = root.getElementsByTagName("MsgType").item(0).getTextContent();
            result[5] = root.getElementsByTagName("Content").item(0).getTextContent();
            result[6] = root.getElementsByTagName("MsgId").item(0).getTextContent();
           /* String s = Arrays.toString(result);*/
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Zzn_Exception("解析失败");
        }
    }

    //形成xml
    public String enXml(String FromUserName, String ToUserName, String Context){

        String xml= "<xml><ToUserName><![CDATA["+FromUserName+"]]></ToUserName><FromUserName><![CDATA["+ToUserName+"]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["+Context+"]]></Content></xml>";

        return  xml;

    }



}
