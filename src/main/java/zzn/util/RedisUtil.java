package zzn.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public void setRedisValue(String  username,String RandomStr){
        stringRedisTemplate.opsForValue().set(username,RandomStr,30,TimeUnit.MINUTES);
    }

    public String getRedisValue(String username){

        String s = stringRedisTemplate.opsForValue().get(username);
        return  s;
    }


    public void setRedisMap(String  username, Map map){

        redisTemplate.opsForValue().set(username,map);


    }
    public Map getRedisMap(String  housenumber){

         Map map=(Map)redisTemplate.opsForValue().get(housenumber);

       return   map;
    }


}
