package zzn.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import zzn.bean.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zzn.bean.Client;
import zzn.dao.ClientDao;
import zzn.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;


/*@RestController*/
@Controller
public class ClientController {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private TokenUtil tokenUtil;
    public  final static Logger log= LoggerFactory.getLogger(Controller.class);

    @ResponseBody
    @RequestMapping("/login")

    public Msg login( @RequestBody Client client ){ return clientDao.login(client); }
    @ResponseBody
    /*@RequestMapping("/register")*/
    @RequestMapping("/register")
    public  Msg register(@Valid @RequestBody Client client) {
        return clientDao.register(client);
    }

    @RequestMapping("/success")
    public  String success(Map<String,Object> map) {
        map.put("hello","thymeleaf");
        //classpath:/templates/success.html
        return "success";
    }

    @RequestMapping("/token")
    @ResponseBody
    public  Msg gettoken(HttpServletRequest request) {

        String gztoken = request.getHeader("gztoken");
        log.info("gztoken===="+gztoken);
        Msg client = tokenUtil.getClient(gztoken);
        log.info("msg========="+client.getData().toString());
        return client;


    }
}
