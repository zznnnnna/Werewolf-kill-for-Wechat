package zzn.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    public  final  static Logger log=LoggerFactory.getLogger(MyErrorAttributes.class);

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
       Map<String,Object> map=super.getErrorAttributes(webRequest, options);
         log.info("map=========="+map);

         String exception=(String) map.get("exception");
         String errorpath=(String)map.get("path");
         String error=(String) map.get("error");
        Map<String,Object> zznMsg= (Map<String, Object>) webRequest.getAttribute("zznMsg",0);
        zznMsg.put("errorTime",new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(map.get("timestamp")));
        zznMsg.put("errorPath",errorpath);
        zznMsg.put("sub_code",exception);
        zznMsg.put("msg",error);
        zznMsg.put("status",map.get("status"));
        Map<String,Object> sendMap=new HashMap<>();
        sendMap.put("error_response",zznMsg);
        log.info("sendMap========"+sendMap);
        return sendMap;

    }

}
