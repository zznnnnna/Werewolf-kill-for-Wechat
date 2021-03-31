package zzn.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import javafx.util.Builder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import zzn.util.WeiXinUtil.WxXml;
import zzn.util.WeiXinUtil.getSHA1;
import zzn.util.WeiXinUtil.getSignature;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.io.ByteStreams.readBytes;
import static com.google.common.io.ByteStreams.toByteArray;


public class test {

    @Test
    public  void random(){

        int[] arr = new int[]{0,1,2,3,4,5,6,7};
        int length = arr.length;
        for(int i=0;i<length;i++){
            int iRandNum = (int)(Math.random() * length);
            int temp = arr[iRandNum];
            arr[iRandNum] = arr[i];
            arr[i] = temp;
        }
        for(int i=0;i<length;i++){
            System.out.print(arr[i] + " ");
        }
    }

    @Test
    public  void testXml() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        String xml="<xml><zzn>11111</zzn></xml>";
        StringReader sr=new StringReader(xml);
        InputSource is=new InputSource(sr);
        Document dc = db.parse(is);
        Element e = dc.getDocumentElement();
        String zzn = e.getElementsByTagName("zzn").item(0).getTextContent();
        System.out.println(zzn);
    }

    @Test
    public void reverse() {
    String str="asdasda";
    System.out.println(str.substring(1,3));

        List<String> list = Arrays.asList(str);
        System.out.println(list);
        System.out.println();



    }

    @Test
    public void dui() {
  StringBuilder stringBuilder=new StringBuilder();
        System.out.println("stringBuilder.capacity() = " + stringBuilder.capacity());
        stringBuilder.append("11111111111111111");
        System.out.println("stringBuilder.capacity() = " + stringBuilder.capacity());
        stringBuilder.append("111111111111111111");
        System.out.println("stringBuilder.capacity() = " + stringBuilder.capacity());
        stringBuilder.append("111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println("stringBuilder.capacity() = " + stringBuilder.capacity());

        stringBuilder.append("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println("stringBuilder.capacity() = " + stringBuilder.capacity());


    }

    @Test
    public void buffer() {
    StringBuffer stringBuffer=new StringBuffer("ssssssss");
    StringBuilder stringBuilder=new StringBuilder();
    String s=new String("Sssssssssssssssssssssss");

    System.out.println(stringBuffer);

    }

    @Test
    public void ssss() {


        String a=new String("aaa");
        String S="aaa";
        String b="aaa";
        System.out.println(S==a);


    }

    @Test
    public  void bytess() throws IOException {
     InputStream is=new FileInputStream("D:\\software\\pb_ydyx_flow_tb20201115.dat");
     System.out.println(is);

     byte[] a=toByteArray(is);

     System.out.println(Arrays.toString(a));
     Map map=new HashMap();
     String  s=a.toString();
        Gson gson=new Gson();
        map=gson.fromJson(s,Map.class);
        System.out.println(map);
    }





}
