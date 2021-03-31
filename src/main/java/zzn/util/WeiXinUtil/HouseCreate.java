package zzn.util.WeiXinUtil;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.BeanListProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import zzn.bean.Client;
import zzn.bean.ZXH;
import zzn.util.RedisUtil;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
 class gameCreate {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private   static  final Logger log= LoggerFactory.getLogger(gameCreate.class);

    public String damaoxian() {

        String sql="select * from ( select * from zxh order by dbms_random.value()) where rownum <7";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        Map map=maps.get(0);


        return "请输的同学摇骰子选择:\n" +
                "\n" +
                "1. "+maps.get(0).get("Q")+"\n" +
                "2. "+maps.get(1).get("Q")+"\n" +
                "3. "+maps.get(2).get("Q")+"\n" +
                "4. "+maps.get(3).get("Q")+"\n" +
                "5. "+maps.get(4).get("Q")+"\n" +
                "6. "+maps.get(5).get("Q")+"\n" +
                "\n" +
                "再次回复【6】，换一批\n" ;

    }
  public String getZhiYeRedis(int housenumber,String FromUserName){
        //获取map
        Map map=redisUtil.getRedisMap(String.valueOf(housenumber));
        if (null==map||"".equals(map)){
            return  "房间号已过期，请重新输入";
        }

        //获取随机生成的map
         //获取随机 职业 数组
        String[]  zhiye= (String[]) map.get("zhiye");
        //获取随机  顺序  数组
        int[]  arr= (int[]) map.get("arr");

        //获取目前人数
        int number= (int) map.get("number");
        if (number>arr.length){

            return  "该房间已满，请更换房间";
        }
        //获取当前 已加入用户 数组
        String[] username=(String[])map.get("username");
        //获取法官 姓名
        String  faguanName=username[0];
        if (faguanName.equals(FromUserName)){
            return "您好，房间号[" +housenumber+"]\n"+
                    "您的职业是：法官\n"+
                    "快将房号告诉小伙伴们";
        }else {
            for (int i=1;i<arr.length;i++) {
                //循环 获取 当前 职业名称
              String zhiyeusername=username[i];
              if (null!=zhiyeusername&&!"".equals(zhiyeusername)){
                  if (zhiyeusername.equals(FromUserName)){

                      return "您好，房间号[" +housenumber+"]\n"+
                              "您是"+arr[i]+"号，您的职业是："+zhiye[i];

                  }

                  else  continue;

              }

            }

        }

        String zy="";
        for (int i=1;i<=arr.length;i++){
            if (number==arr[i]){

                zy=zhiye[i];
                username[i]=FromUserName;

                break;
            }

        }
        map.put("username",username);

        map.put("number",number+1);

        redisUtil.setRedisMap(String.valueOf(housenumber),map);

      return "您好，房间号[" +housenumber+"]\n"+
              "您是"+number+"号，您的职业是："+zy;
  }





   public void setZhiYeRedis(int housenumber,String[] zhiye,int[]  arr,String FromUserName){
       Map map=new HashMap();
       map.put("arr",arr);
       map.put("zhiye",zhiye);
       map.put("number",1);
       int i=arr.length;
       String[] username=new String[i];
       username[0]=FromUserName;
       map.put("username",username);

       log.info("map======"+map);

       redisUtil.setRedisMap(String.valueOf(housenumber),map);
   }


    public  String createHouse(Integer  number,Integer housenumber,String FromUserName){

        if (number==6){                 //0     1      2       3      4      5      6
            String[] zhiye=new String[] {"法官","狼人","预言家","女巫","丘比特","村民","村民"};
            int[] arr=ArrRandom(6);

            log.info("职业======="+zhiye);
            log.info("数组======="+arr);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);
            log.info("放入 职业 数组 map成功");
            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共6人，1狼人\n" +
                    "狼    人："+arr[1]+"号\n" +
                    "预言家："+arr[2]+"号\n" +
                    "女    巫："+arr[3]+"号\n" +
                    "丘比特："+arr[4]+"号\n" +
                    "村    民："+arr[5]+"号，"+arr[6]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }

        else if (number==7){
                                         //0     1      2       3      4      5      6    7
            String[] zhiye=new String[] {"法官","狼人","预言家","女巫","丘比特","村民","村民","村民"};
            int[] arr=ArrRandom(7);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return    "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共7人，1狼人\n" +
                    "狼    人："+arr[1]+"号\n" +
                    "预言家："+arr[2]+"号\n" +
                    "女    巫："+arr[3]+"号\n" +
                    "丘比特："+arr[4]+"号\n" +
                    "村    民："+arr[5]+"号，"+arr[6]+"号"+arr[7]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }else if (number==8){

                                         //0     1      2       3      4      5      6    7     8
            String[] zhiye=new String[] {"法官","狼人","狼人","预言家","女巫","丘比特","猎人","村民","村民"};
            int[] arr=ArrRandom(8);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共8人，2狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号\n" +
                    "预言家："+arr[3]+"号\n" +
                    "女    巫："+arr[4]+"号\n" +
                    "丘比特："+arr[5]+"号\n" +
                    "猎    人："+arr[6]+"号\n" +
                    "村    民："+arr[7]+"号，"+arr[8]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";

        }else if (number==9){

                                          //0     1      2       3      4      5    6    7     8     9
            String[] zhiye=new String[] {"法官","狼人","狼人","预言家","女巫","丘比特","猎人","村民","村民","村民"};
            int[] arr=ArrRandom(9);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共9人，2狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号\n" +
                    "预言家："+arr[3]+"号\n" +
                    "女    巫："+arr[4]+"号\n" +
                    "丘比特："+arr[5]+"号\n" +
                    "猎    人："+arr[6]+"号\n" +
                    "村    民："+arr[7]+"号，"+arr[8]+"号"+arr[9]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }else if (number==10){
                                         //0     1      2      3      4      5      6    7      8     9    10
            String[] zhiye=new String[] {"法官","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","村民","村民","村民"};
            int[] arr=ArrRandom(10);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);


            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                 "房    号："+housenumber+"\n" +
                 "配    置：共10人，2狼人\n" +
                 "狼    人："+arr[1]+"号，"+arr[2]+"号\n" +
                 "预言家："+arr[3]+"号\n" +
                 "女    巫："+arr[4]+"号\n" +
                 "丘比特："+arr[5]+"号\n" +
                 "猎    人："+arr[6]+"号\n" +
                 "守    卫："+arr[7]+"号\n" +
                 "村    民："+arr[8]+"号，"+arr[9]+"号"+arr[10]+"号\n" +
                 "\n" +
                 "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==11){
                                         //0     1      2      3      4      5      6    7      8     9    10    11
            String[] zhiye=new String[] {"法官","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民"};
            int[] arr=ArrRandom(11);

            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);


            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                  "房    号："+housenumber+"\n" +
                  "配    置：共11人，2狼人\n" +
                  "狼    人："+arr[1]+"号，"+arr[2]+"号\n" +
                  "预言家："+arr[3]+"号\n" +
                  "女    巫："+arr[4]+"号\n" +
                  "丘比特："+arr[5]+"号\n" +
                  "猎    人："+arr[6]+"号\n" +
                  "守    卫："+arr[7]+"号\n" +
                  "白    痴："+arr[8]+"号\n" +
                  "村    民："+arr[9]+"号，"+arr[10]+"号"+arr[11]+"号\n" +
                  "\n" +
                  "游戏结束后，回复【6】，查看大冒险惩罚！";
        }else if (number==12){

                                          //0     1      2      3      4      5      6    7      8     9    10    11   12
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民"};
            int[] arr=ArrRandom(12);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                 "房    号："+housenumber+"\n" +
                 "配    置：共12人，3狼人\n" +
                 "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号\n" +
                 "预言家："+arr[4]+"号\n" +
                 "女    巫："+arr[5]+"号\n" +
                 "丘比特："+arr[6]+"号\n" +
                 "猎    人："+arr[7]+"号\n" +
                 "守    卫："+arr[8]+"号\n" +
                 "白    痴："+arr[9]+"号\n" +
                 "村    民："+arr[10]+"号，"+arr[11]+"号"+arr[12]+"号\n" +
                 "\n" +
                 "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==13){

                                         //0     1      2      3      4      5      6    7      8     9    10     11     12    13
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民","村民"};
            int[] arr=ArrRandom(13);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共13人，3狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号\n" +
                    "预言家："+arr[4]+"号\n" +
                    "女    巫："+arr[5]+"号\n" +
                    "丘比特："+arr[6]+"号\n" +
                    "猎    人："+arr[7]+"号\n" +
                    "守    卫："+arr[8]+"号\n" +
                    "白    痴："+arr[9]+"号\n" +
                    "村    民："+arr[10]+"号，"+arr[11]+"号，"+arr[12]+"号，"+arr[13]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==14){

                                         //0     1      2      3      4      5      6    7      8     9    10     11     12    13   14
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民","村民","村民"};
            int[] arr=ArrRandom(14);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共14人，3狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号\n" +
                    "预言家："+arr[4]+"号\n" +
                    "女    巫："+arr[5]+"号\n" +
                    "丘比特："+arr[6]+"号\n" +
                    "猎    人："+arr[7]+"号\n" +
                    "守    卫："+arr[8]+"号\n" +
                    "白    痴："+arr[9]+"号\n" +
                    "村    民："+arr[10]+"号，"+arr[11]+"号，"+arr[12]+"号，"+arr[13]+"号，"+arr[14]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==15){
                                          //0     1      2      3      4      5      6    7      8     9    10     11    12    13   14     15
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民","村民","村民","村民"};
            int[] arr=ArrRandom(15);

            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共15人，3狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号\n" +
                    "预言家："+arr[4]+"号\n" +
                    "女    巫："+arr[5]+"号\n" +
                    "丘比特："+arr[6]+"号\n" +
                    "猎    人："+arr[7]+"号\n" +
                    "守    卫："+arr[8]+"号\n" +
                    "白    痴："+arr[9]+"号\n" +
                    "村    民："+arr[10]+"号，"+arr[11]+"号，"+arr[12]+"号，"+arr[13]+"号，"+arr[14]+"号，"+arr[15]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==16){
                                           //0     1      2      3      4      5      6    7      8     9    10     11    12    13   14     15   16
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民","村民","村民","村民","村民"};
            int[] arr=ArrRandom(16);

            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共16人，3狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号\n" +
                    "预言家："+arr[4]+"号\n" +
                    "女    巫："+arr[5]+"号\n" +
                    "丘比特："+arr[6]+"号\n" +
                    "猎    人："+arr[7]+"号\n" +
                    "守    卫："+arr[8]+"号\n" +
                    "白    痴："+arr[9]+"号\n" +
                    "村    民："+arr[10]+"号，"+arr[11]+"号，"+arr[12]+"号，"+arr[13]+"号，"+arr[14]+"号，"+arr[15]+"号，"+arr[16]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==17){
                                         //0    1      2      3     4      5      6    7      8       9     10     11    12    13    14    15    16    17
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民","村民","村民","村民","村民"};
            int[] arr=ArrRandom(17);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

             return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                     "房    号："+housenumber+"\n" +
                     "配    置：共17人，4狼人\n" +
                     "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号，"+arr[4]+"号\n" +
                     "预言家："+arr[5]+"号\n" +
                     "女    巫："+arr[6]+"号\n" +
                     "丘比特："+arr[7]+"号\n" +
                     "猎    人："+arr[8]+"号\n" +
                     "守    卫："+arr[9]+"号\n" +
                     "白    痴："+arr[10]+"号\n" +
                     "村    民："+arr[11]+"号，"+arr[12]+"号，"+arr[13]+"号，"+arr[14]+"号，"+arr[15]+"号，"+arr[16]+"号，"+arr[17]+"号\n" +
                     "\n" +
                     "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==18){
                                          //0    1      2      3     4      5      6    7      8       9     10     11    12    13    14    15    16    17    18
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民","村民","村民","村民","村民","村民"};
            int[] arr=ArrRandom(18);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共18人，4狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号，"+arr[4]+"号\n" +
                    "预言家："+arr[5]+"号\n" +
                    "女    巫："+arr[6]+"号\n" +
                    "丘比特："+arr[7]+"号\n" +
                    "猎    人："+arr[8]+"号\n" +
                    "守    卫："+arr[9]+"号\n" +
                    "白    痴："+arr[10]+"号\n" +
                    "村    民："+arr[11]+"号，"+arr[12]+"号，"+arr[13]+"号，"+arr[14]+"号，"+arr[15]+"号，"+arr[16]+"号，"+arr[17]+"号，"+arr[18]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==19){
                                          //0    1      2      3     4      5      6    7      8       9     10     11    12    13    14    15    16    17    18     19
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民","村民","村民","村民","村民","村民","村民"};
            int[] arr=ArrRandom(19);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共19人，4狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号，"+arr[4]+"号\n" +
                    "预言家："+arr[5]+"号\n" +
                    "女    巫："+arr[6]+"号\n" +
                    "丘比特："+arr[7]+"号\n" +
                    "猎    人："+arr[8]+"号\n" +
                    "守    卫："+arr[9]+"号\n" +
                    "白    痴："+arr[10]+"号\n" +
                    "村    民："+arr[11]+"号，"+arr[12]+"号，"+arr[13]+"号，"+arr[14]+"号，"+arr[15]+"号，"+arr[16]+"号，"+arr[17]+"号，"+arr[18]+"号，"+arr[19]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }
        else if (number==20){
                                          //0    1      2      3     4      5      6    7      8       9     10     11    12    13    14    15    16    17    18     19   20
            String[] zhiye=new String[] {"法官","狼人","狼人","狼人","狼人","狼人","预言家","女巫","丘比特","猎人","守卫","白痴","村民","村民","村民","村民","村民","村民","村民","村民","村民"};
            int[] arr=ArrRandom(20);
            setZhiYeRedis(housenumber,zhiye,arr,FromUserName);

            return "建房成功！您是法官，请把房间号告诉参与游戏的玩家。\n" +
                    "房    号："+housenumber+"\n" +
                    "配    置：共20人，5狼人\n" +
                    "狼    人："+arr[1]+"号，"+arr[2]+"号，"+arr[3]+"号，"+arr[4]+"号，"+arr[5]+"号\n" +
                    "预言家："+arr[6]+"号\n" +
                    "女    巫："+arr[7]+"号\n" +
                    "丘比特："+arr[8]+"号\n" +
                    "猎    人："+arr[9]+"号\n" +
                    "守    卫："+arr[10]+"号\n" +
                    "白    痴："+arr[11]+"号\n" +
                    "村    民："+arr[12]+"号，"+arr[13]+"号，"+arr[14]+"号，"+arr[15]+"号，"+arr[16]+"号，"+arr[17]+"号，"+arr[18]+"号，"+arr[19]+"号，"+arr[20]+"号\n" +
                    "\n" +
                    "游戏结束后，回复【6】，查看大冒险惩罚！";
        }

        redisUtil.setRedisValue(FromUserName,"1");
        return "输入的必须是6-20之间的数字哦，请重新输入：";
    }


    public  int[] ArrRandom(int number){

        int arr[]=new int[number];
        for (int i=1;i<=arr.length;i++){
            arr[i-1]=i;
        }
        int length = arr.length;
        for(int i=0;i<length;i++){
            int iRandNum = (int)(Math.random() * length);
            int temp = arr[iRandNum];
            arr[iRandNum] = arr[i];
            arr[i] = temp;
        }
        int[] arr1=new int[number+1];
        arr1[0]=0;
        /*int i=arr.length;*/
        for (int i=1;i<=arr.length;i++){
            arr1[i]=arr[i-1];
        }
      return arr1;
    }





}
