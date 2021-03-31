package zzn.dao.DaoImpl;
import zzn.ExceptionHandler.Zzn_Exception;
import zzn.bean.Client;
import zzn.bean.Msg;
import zzn.controller.ClientController;
import zzn.dao.ClientDao;
import zzn.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import zzn.util.TokenUtil;


@Component
public class ClientDaoImpl implements ClientDao {
    public  static  final Logger log=  LoggerFactory.getLogger(ClientController.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Msg msg;
    @Autowired
    private Md5Util md5Util;
    @Autowired
    private TokenUtil tokenUtil;


    @Override
    public Msg login(Client clien) {
        String username=clien.getUsername();
        String password=clien.getPassword();
        log.info("登录开始================================");

        String md5=md5Util.getMd5Util(username,password);
        Client client=findClient(username,password);
        if (client==null){

            throw new Zzn_Exception("用户不存在，请重新输入或注册新用户");}
        else if (!md5.equals(client.getPassword())) {
            throw  new Zzn_Exception("密码错误");
        } else {
            String token = tokenUtil.setToken(username);
            msg.setToken(token);
            client.setPassword("********");
            return msg;
        }
    }
    @Override
    public Msg register(Client client) {

        log.info("注册开始================================");
        String sql = "insert into client  values(?,?,?,?,?,?,?)";
        String username=client.getUsername();
        String password=client.getPassword();
        Client clie=findClient(username,password);
        if (clie!=null){throw  new Zzn_Exception("用户已存在");}

        String md5password=md5Util.getMd5Util(username,password);
        int update=0;
        try{log.info("前台注册数据="+client);
            update = jdbcTemplate.update(sql,null, client.getUsername(),md5password, client.getEmail(), client.getMobilePhone(), client.isVip(), client.getEndDate());

        }catch (Exception e){
            throw  new Zzn_Exception(e.getMessage());
        }
        if (update == 0) {
            throw  new Zzn_Exception("注册失败");
        }
        client.setPassword("********");    
        return msg.successMsg( client);
    }

    @Override
    public Client findClient(String username, String password) {
        String sql = "select * from client where  username=?";
        String md5=md5Util.getMd5Util(username,password);
        Client client = null;
        try {
            log.info("前台输入的用户名=" + username + "前台输入的密码=:" + password);
            client = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Client>(Client.class), username);
        }
        catch (Exception e){

            return   null;
        }

            return client;
    }

    @Override
    public Client findClient(String username) {


        String sql = "select * from client where  username=?";
        Client client = null;
        try {
            client = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Client>(Client.class), username);
        }
        catch (Exception e){

            return   null;
        }

        return client;
    }


}