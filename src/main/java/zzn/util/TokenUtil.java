package zzn.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zzn.ExceptionHandler.Zzn_Exception;
import zzn.bean.Client;
import zzn.bean.Msg;
import zzn.dao.ClientDao;
import zzn.dao.DaoImpl.ClientDaoImpl;

@Component
public class TokenUtil {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private  ZznUtil zznUtil;
    @Autowired
    private RedisUtil redisUtil;


    //生成token
    public String setToken(String username){


        String RandomStr=zznUtil.generateRandomStr(32);
        redisUtil.setRedisValue(username,RandomStr);
        String token="";
        token=JWT.create().withAudience(username).sign(Algorithm.HMAC256(RandomStr));
        return token;
    }

    //token检验
    public Msg SignMsg(String token,String username) {
        String RandomStr=redisUtil.getRedisValue(username);
        Msg msg = new Msg();
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(RandomStr)).build();

        try {
            jwtVerifier.verify(token);
            msg.setMsg("token验证成功");
            return msg;
        } catch (Exception e) {
            throw new Zzn_Exception("token验证失败");
        }

    }


    //获取username
    public Msg getClient(String token){

        Msg msg=new Msg();

        String username = JWT.decode(token).getAudience().get(0);
        Client client = clientDao.findClient(username);
        msg.setData(client);
        msg.setMsg("token验证成功");
        return msg;

        }

}
