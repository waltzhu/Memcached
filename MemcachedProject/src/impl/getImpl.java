package impl;

import java.util.Iterator;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class getImpl {

	protected static MemCachedClient mcc = new MemCachedClient(); 
	
    static  
    {  
        // 设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器  
        String[] servers =  
        {  
                "127.0.0.1:11211"  
                //"server.mydomain.com:11211"  
        };  
  
        // 设置服务器权重  
        Integer[] weights = {3, 2};  
  
        // 创建一个Socked连接池实例  获取socke连接池的实例对象    
        SockIOPool pool = SockIOPool.getInstance();  
  
        // 向连接池设置服务器和权重  
        pool.setServers(servers);  
        pool.setWeights(weights);  
          
        // 设置初始连接数、最小和最大连接数以及最大处理时间    
        pool.setInitConn(5);    
        pool.setMinConn(5);    
        pool.setMaxConn(250);    
        pool.setMaxIdle(1000 * 60 * 60 * 6);   
          
        // 设置主线程的睡眠时间    
        pool.setMaintSleep(30);    
          
        // 设置TCP的参数，连接超时等    
        pool.setNagle(false);  
        pool.setSocketTO(3000);  
        pool.setSocketConnectTO(0);  
  
        // 初始化连接池    
        pool.initialize();  
    }  
      
    public static void main(String[] args)  
    {  
    	1.取key
        System.out.println(">>> " +getByKey("waltzhu"));   
    	
    	2.加key-add
    	addKey("waltzhu","123456");
    	System.out.println(">>> " +getByKey("waltzhu"));
    	
    	3.加key-set
    	setKey("waltzhu","110");
    	System.out.println(">>> " +getByKey("waltzhu"));
    	
    	4.replace
    	replaceKey("waltzhu","110");
    	System.out.println(">>> " +getByKey("waltzhu"));
    	
    	5.delete
    	deleteKey("waltzhu");
    	System.out.println(">>> " +getByKey("waltzhu"));
    	
    	
    	7.append
    	appends("waltzhu","120");
    	System.out.println(">>> " +getByKey("waltzhu"));
    	
    	8.prepend
    	prepend("waltzhu","999");
    	System.out.println(">>> " +getByKey("waltzhu"));

    	9.status
    	Map statsMap=stats();
    	Iterator<Map.Entry>  it=statsMap.entrySet().iterator();
        if(it.hasNext()){
        	Map.Entry entry=it.next();
        	System.out.println(entry.getKey()+"*****"+entry.getValue());
        }
    	
    	
    }  
    
    //1.获取Key
    private static String getByKey(String key){
    	 String name = mcc.get(key).toString();  
    	 return name;
    }
    
    //2.增加Key-add
    private static void addKey(String key,String value){
    	mcc.add(key,value);
    }
    
    //3.增加key-set
    private static void setKey(String key,String value){
    	mcc.set(key, value);
    }
    
    //4.replace
    private static void replaceKey(String key,String value){
    	mcc.replace(key, value);
    }
    
    //5.delete 
    private static void deleteKey(String key){
    	mcc.delete(key);
    }
    
    //6.flush-all
    private static void flushAll(){
    	mcc.flushAll();
    }
    
    //7.append
    private static void appends(String key,String apStr){
    	mcc.append(key, apStr);
    }
    
    //8.prepend
    private static void prepend(String key,String value){
    	mcc.prepend(key, value);
    }
    
    //9.stats
    private static Map stats(){
    	Map m=mcc.stats();
    	return m;
    }
    
    
}
