package zzn.util.WeiXinUtil;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import zzn.ExceptionHandler.Zzn_Exception;

import java.security.MessageDigest;
import java.util.Arrays;
@Component
public class getSHA1 {
    private  String  token="gylzzn";

    public  String getSHA1(String timestamp, String nonce)
    {
        try {
            String[] array = new String[] { token, timestamp, nonce};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 3; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Zzn_Exception("SHA1加密失败");
        }
    }
}




