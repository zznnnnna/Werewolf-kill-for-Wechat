package zzn;



/*
 * springboot 单元测试类
 * 可以在测试期间很方便的类似编码一样自动注入容器的功能
 *
 * */


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.test.context.SpringBootTest;
import zzn.ExceptionHandler.Zzn_Exception;

import java.util.List;

/*
@RunWith(SpringRunner.class)
*/
@SpringBootTest
public class DemoApplicationTests {

    public static void main(String[] args) {

         String token="";
         token= JWT.create().withAudience("zzz").sign(Algorithm.HMAC256("123123"));
         System.out.println(token);

         //获取userid
         String userid=JWT.decode(token).getAudience().get(0);
         System.out.println("withAudience=="+userid);

        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123123")).build();
        try {
            DecodedJWT verify = jwtVerifier.verify(token+"ssss");
            System.out.println("token验证成功");
        }catch (Exception e){
            throw  new Zzn_Exception("用户没权限");
        }




    }


}
