package zzn.util;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
@Component
public class Md5Util {

    public static void main(String[] args) {
       Md5Util M=new Md5Util();
        String md5Util = M.getMd5Util("zzn","123123");
        System.out.println(md5Util);


    }
    public String getMd5Util(String username,String  password){
        String  md5001 = "";
        String  md5002 = "";
        String  md5="";
        try {
            md5001=DigestUtils.md5DigestAsHex("zzngyl".getBytes("UTF-8"));
            md5002=DigestUtils.md5DigestAsHex((username+md5001).getBytes("UTF-8"));
            md5 =DigestUtils.md5DigestAsHex((md5002+password).getBytes("UTF-8"));

        }catch (Exception e){
            System.out.println();
        }

        return md5;
    }




}
