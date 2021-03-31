package zzn.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zzn.util.WeiXinUtil.GameUtil;
import zzn.util.WeiXinUtil.WxXml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;


@Controller
public class WeiXinController {

    @Autowired
    private zzn.util.WeiXinUtil.getSHA1 getSHA1;


    @Autowired
    private WxXml wxXml;
    @Autowired
    private GameUtil gameUtil;


    private  static  final Logger log=LoggerFactory.getLogger(WeiXinController.class);

    @ResponseBody
   @GetMapping("/wx/portal/{appid}")
    public String settoken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");   //返回这个就可以验证token

        return echostr;

    }
    @ResponseBody
    @GetMapping("/checkWeXin")
    public String getCheckWeXin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");   //返回这个就可以验证token

        return echostr;

    }
    @PostMapping("/checkWeXin")
    public void postWeXin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String signature = request.getParameter("signature");  String timestamp = request.getParameter("timestamp");String nonce = request.getParameter("nonce");String openid = request.getParameter("openid");
        log.info("signature====" + signature);log.info("timestamp=====" + timestamp);log.info("nonce===" + nonce);log.info("openid===" + openid);
        //获取用户信息
        //xml是微信发的信息
        String xml = wxXml.getXml(request);
        log.info("获取的xml是====" + xml);
        //解析xml
        String[] getxml = wxXml.deXml(xml);
        String ToUserName = getxml[1].trim();
        String FromUserName = getxml[2].trim();
        String Content = getxml[5].trim();


        String s = gameUtil.plyaGames(FromUserName, ToUserName, Content);
        response.getWriter().print(s);



    }




    @PostMapping("/wx/portal/{appid}")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String signature = request.getParameter("signature");  String timestamp = request.getParameter("timestamp");String nonce = request.getParameter("nonce");String openid = request.getParameter("openid");
        log.info("signature====" + signature);log.info("timestamp=====" + timestamp);log.info("nonce===" + nonce);log.info("openid===" + openid);
        //获取用户信息
        //xml是微信发的信息
        String xml = wxXml.getXml(request);
        log.info("获取的xml是====" + xml);
        //解析xml
        String[] getxml = wxXml.deXml(xml);
        String ToUserName = getxml[1].trim();
        String FromUserName = getxml[2].trim();
        String Content = getxml[5].trim();


        String s = gameUtil.plyaGames(FromUserName, ToUserName, Content);
        response.getWriter().print(s);



    }


}
