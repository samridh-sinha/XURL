
package com.crio.shorturl;

import java.util.HashMap;

public class XUrlImpl implements XUrl {
    
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private HashMap<String,String> map  = new HashMap<>(); 
    public HashMap<String,Integer> countMap = new HashMap<>();
    @Override
    public String registerNewUrl(String longUrl) {

        if(map.containsKey(longUrl)) 
            return map.get(longUrl);
        
        String random = this.generate(); 
        String shortString = "http://short.url/";   
        String shortUrl = shortString.concat(random); 
        countMap.put(shortUrl, 0);
        map.put(longUrl, shortUrl); 

        return map.get(longUrl);
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {

        for (HashMap.Entry<String,String> entry : map.entrySet()){
            if(entry.getValue().equals(shortUrl))
                return null;
        }  
        countMap.put(shortUrl, 0);
        map.put(longUrl, shortUrl); 

        return map.get(longUrl);
        
    }

    @Override
    public String getUrl(String shortUrl) {

        for (HashMap.Entry<String,String> entry : map.entrySet()){
            
            if(entry.getValue().equals(shortUrl)){
                countHelper(shortUrl);
                return entry.getKey();
                
            }
                
        }
        return null;
    } 

    private void countHelper(String shortUrl){ 

            countMap.put(shortUrl, countMap.get(shortUrl)+1); 

    }

    @Override
    public Integer getHitCount(String longUrl) {
        
        String shortUrl = map.get(longUrl);  
        if(countMap.containsKey(shortUrl)==false)
            return 0;
        Integer hitCount = countMap.get(shortUrl); 
        //System.out.println(countMap);
        return hitCount;
    }

    @Override
    public String delete(String longUrl) {
        
        if(map.containsKey(longUrl))
            map.remove(longUrl);
        return null;
    } 

    private String generate(){
        int count = 9;  
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
        return builder.toString();
        
    } 
    public void getCountMap(){
        System.out.println(countMap);
    }

}