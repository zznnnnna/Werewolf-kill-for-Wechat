package zzn.dao;
import zzn.bean.Client;
import zzn.bean.Msg;

public interface ClientDao {

     Msg login(Client client);
     Msg register(Client client);
     Client findClient(String username,String password);
     Client findClient(String username);
}
