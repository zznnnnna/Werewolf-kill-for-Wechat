package zzn.util.WeiXinUtil;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zzn.util.RedisUtil;

import java.net.URLEncoder;
import java.util.Random;

@Component
public class GameUtil {
@Autowired
private  WxXml wxXml;
@Autowired
private RedisUtil redisUtil;
@Autowired
private gameCreate gameCreate;

public  static  final Logger log= LoggerFactory.getLogger(GameUtil.class);

    public  String  plyaGames(String FromUserName, String ToUserName, String Context){



        String openidValue=redisUtil.getRedisValue(FromUserName);
        if (null==openidValue||""==openidValue){

            try{
                Integer msg=Integer.valueOf(Context);//现在送的信息


                if (msg==1){
                    //上来就输入1就是要玩 狼人杀

                    redisUtil.setRedisValue(FromUserName,Context);
                    return wxXml.enXml(FromUserName,ToUserName,"准备创建狼人杀，请输入游戏人数(6-20之间，不包括法官哦):");

                 }
                if (msg==2){
                    redisUtil.setRedisValue(FromUserName,Context);
                    return wxXml.enXml(FromUserName,ToUserName,"谁是卧底还未上线哦，您可以输入1选择创建狼人杀:");

                }
                if (msg==6){
                    //上来就输入6就是要玩 大冒险
                    log.info("走到了大冒险");
                    String remsg=gameCreate.damaoxian();
                    return wxXml.enXml(FromUserName, ToUserName, remsg);

                }
                else if (msg>999&&msg<10000){
                    //上来就输入999-10000 就是要进入房间
                    log.info("要加入房间");
                    String remsg=gameCreate.getZhiYeRedis(msg,FromUserName);
                    return wxXml.enXml(FromUserName, ToUserName, remsg);
                }


                else {
                    redisUtil.setRedisValue(FromUserName,Context);
                    return wxXml.enXml(FromUserName,ToUserName,"您好，这里是[奇怪的楠],你可以回复以下相应数字，火速开始游戏\n\t1.快速开始狼人杀 \n\t2.快速开始谁是卧底");
                }

            }catch (Exception e){



                redisUtil.setRedisValue(FromUserName,Context);
                return wxXml.enXml(FromUserName,ToUserName,"您好，这里是[奇怪的楠],你可以回复以下相应数字，火速开始游戏\n\t1.快速开始狼人杀 \n\t2.快速开始谁是卧底");


            }


        }else if (null!=openidValue&&""!=openidValue)
        {
            try{
                Integer num=Integer.valueOf(openidValue);//数据库的值

                Integer msg=Integer.valueOf(Context);//现在送的信息
                log.info("数据库的值是：num"+num);
                log.info("当前用户输入的值是：msg"+msg);
                log.info("走到了try：");
                if (1==msg){
                    log.info("走到了第一个if：");
                    redisUtil.setRedisValue(FromUserName,Context);
                    return wxXml.enXml(FromUserName,ToUserName,"准备创建狼人杀，请输入游戏人数(6-20之间，不包括法官哦):");

                }
                if (msg==2){
                    redisUtil.setRedisValue(FromUserName,Context);
                    return wxXml.enXml(FromUserName,ToUserName,"谁是卧底还未上线哦，您可以输入1选择创建狼人杀:");

                }
                else if(num==1&&msg<1000) {
                    log.info("走到了第二个if：");
                    redisUtil.setRedisValue(FromUserName,Context);
                    Random random = new Random();
                    int houseNum = random.nextInt(10000)%(10000-1000+1) + 1000;

                  /*  Integer  houseNum=new Random().nextInt(9999);*/
                    log.info("房间号出来了："+houseNum);
                    String remsg=gameCreate.createHouse(msg,houseNum,FromUserName);
                    log.info("返回的信息"+remsg);
                    return wxXml.enXml(FromUserName, ToUserName, remsg);

                }else if(msg==6&&num<=20&&num>=4) {
                    //选择过人数  然后开始输入6  就是大冒险
                    log.info("走到了大冒险");
                    String remsg=gameCreate.damaoxian();
                    return wxXml.enXml(FromUserName, ToUserName, remsg);

                }
                else if(msg<10000&&msg>999) {
                   //在1000和10000中间说明是加入房间
                    log.info("要加入房间");

                    String remsg=gameCreate.getZhiYeRedis(msg,FromUserName);
                    return wxXml.enXml(FromUserName, ToUserName, remsg);

                }

                else {

                    return wxXml.enXml(FromUserName,ToUserName,"您好，这里是[奇怪的楠],你可以回复以下相应数字，火速开始游戏\n\t1.快速开始狼人杀 \n\t2.快速开始谁是卧底");

                }


            }
            catch (Exception e){


                log.info("异常了：");
                return wxXml.enXml(FromUserName,ToUserName,"您好，这里是[奇怪的楠],你可以回复以下相应数字，火速开始游戏\n\t1.快速开始狼人杀 \n\t2.快速开始谁是卧底");
            }




        }



        return null;
    }






}
