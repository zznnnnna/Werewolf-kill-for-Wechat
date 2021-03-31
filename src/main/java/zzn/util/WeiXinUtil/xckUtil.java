package zzn.util.WeiXinUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zzn.util.RedisUtil;

import java.net.URLEncoder;
import java.util.Random;

@Component
public class xckUtil {
@Autowired
private  WxXml wxXml;
@Autowired
private RedisUtil redisUtil;
@Autowired
private gameCreate gameCreate;

public  static  final Logger log= LoggerFactory.getLogger(xckUtil.class);

    public  String  makeXCK(String FromUserName, String ToUserName, String Context) {


        String openidValue = redisUtil.getRedisValue(FromUserName);
        if (null == openidValue || "" == openidValue) {


            if (Context.equals("++++")) {
                redisUtil.setRedisValue(FromUserName, Context);
                return wxXml.enXml(FromUserName, ToUserName, "P+A Start !!!");

            } else
                redisUtil.setRedisValue(FromUserName, Context);
            return wxXml.enXml(FromUserName, ToUserName, "What you say?");

        } else if (openidValue.equals("++++")) {
            String str = Context;
            String[] data = str.split("[+]");
            if (data.length == 2) {
                redisUtil.setRedisValue(FromUserName, "++++");
                String encode = URLEncoder.encode(data[1]);
                return wxXml.enXml(FromUserName, ToUserName, "http://zhaozenan.com/data/1.html?a=" + encode + "&p=" + data[0]);

            } else if (Context.equals("++++")) {
                redisUtil.setRedisValue(FromUserName, "++++");
                return wxXml.enXml(FromUserName, ToUserName, "P+A Start Yet!!!");

            } else {
                redisUtil.setRedisValue(FromUserName, Context);
                return wxXml.enXml(FromUserName, ToUserName, "P+A Stop !!!");

            }


        } else if (Context.equals("++++")) {
            redisUtil.setRedisValue(FromUserName, Context);
            return wxXml.enXml(FromUserName, ToUserName, "P+A Start !!!");

        }

        log.info("异常了：");
        return wxXml.enXml(FromUserName, ToUserName, "What you say?");

    }






}
