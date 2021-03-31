package zzn.util.WeiXinUtil;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
@Component
public class getSignature {

    private  String  token="gylzzn";

    public  boolean checkSignature(String signature,
            String timestamp,String nonce){
               //将传入参数变成一个String数组然后进行字典排序
                String[] arr=new String[]{token,timestamp,nonce};
                Arrays.sort(arr);
              //创建一个对象储存排序后三个String的结合体
              StringBuilder content=new StringBuilder();
              for(int i=0;i<arr.length;i++){
                     content.append(arr[i]);
                   }


              //启动sha1加密法的工具
              MessageDigest md=null;
             String tmpStr=null;
             try {
                       md=MessageDigest.getInstance("SHA-1");
                      //md.digest()方法必须作用于字节数组
                       byte[] digest=md.digest(content.toString().getBytes());
                      //将字节数组弄成字符串
                       tmpStr=byteToStr(digest);
                 } catch (NoSuchAlgorithmException e) {
                       // TODO Auto-generated catch block
                        e.printStackTrace();
                   }
               content=null;

               return tmpStr!=null?tmpStr.equals(signature.toUpperCase()):false;

           }


          /**
 53      * 将字节加工然后转化成字符串
 54      * @param digest
 55      * @return
 56      */
             private String byteToStr(byte[] digest){
                 String strDigest="";
                 for(int i=0;i<digest.length;i++){
                         //将取得字符的二进制码转化为16进制码的的码数字符串
                         strDigest+=byteToHexStr(digest[i]);
                     }
                 return strDigest;
             }

            /**
    * 把每个字节加工成一个16位的字符串
 68      * @param b
      * @return
 70      */
             public  String byteToHexStr(byte b){
                //转位数参照表
                char[] Digit= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


                //位操作把2进制转化为16进制
               char[] tempArr=new char[2];
                tempArr[0]=Digit[(b>>>4)&0X0F];//XXXX&1111那么得到的还是XXXX
                 tempArr[1]=Digit[b&0X0F];//XXXX&1111那么得到的还是XXXX

               //得到进制码的字符串
                String s=new String(tempArr);
               return s;
           }



}
