package zzn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tet {
    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("CardType","151351");
        Map map2=new LinkedHashMap<String,String>();
        map2.put("主卡卡片版面代码&AP_CARD_CODE",map.get("CardType"));
        System.out.println(map2);




    }

}
