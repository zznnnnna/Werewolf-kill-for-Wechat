package zzn.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class MyExceptionHandler  {
public  static  final Logger log= LoggerFactory.getLogger(MyExceptionHandler.class);

   @ExceptionHandler
   public String  handlerException(Exception e, HttpServletRequest request){
       Map<String,Object> map=new HashMap<String,Object>();
       Integer status=(Integer) request.getAttribute("javax.servlet.error.status_code");
       log.info("status=========="+status);
      if (null==status||404!=status){request.setAttribute("javax.servlet.error.status_code",500);}
      log.info("ssssssssssssss"+e.getMessage());
       map.put("sub_msg",e.getMessage());
       map.put("code","50");
       request.setAttribute("zznMsg",map);
       return  "forward:/error";
   }


}
