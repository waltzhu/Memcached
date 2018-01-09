package impl;

import java.util.Iterator;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class getImpl {

	protected static MemCachedClient mcc = new MemCachedClient(); 
	
    static  
    {  
        // ���û���������б���ʹ�÷ֲ�ʽ�����ʱ������ָ��������������  
        String[] servers =  
        {  
                "127.0.0.1:11211"  
                //"server.mydomain.com:11211"  
        };  
  
        // ���÷�����Ȩ��  
        Integer[] weights = {3, 2};  
  
        // ����һ��Socked���ӳ�ʵ��  ��ȡsocke���ӳص�ʵ������    
        SockIOPool pool = SockIOPool.getInstance();  
  
        // �����ӳ����÷�������Ȩ��  
        pool.setServers(servers);  
        pool.setWeights(weights);  
          
        // ���ó�ʼ����������С������������Լ������ʱ��    
        pool.setInitConn(5);    
        pool.setMinConn(5);    
        pool.setMaxConn(250);    
        pool.setMaxIdle(1000 * 60 * 60 * 6);   
          
        // �������̵߳�˯��ʱ��    
        pool.setMaintSleep(30);    
          
        // ����TCP�Ĳ��������ӳ�ʱ��    
        pool.setNagle(false);  
        pool.setSocketTO(3000);  
        pool.setSocketConnectTO(0);  
  
        // ��ʼ�����ӳ�    
        pool.initialize();  
    }  
      
    public static void main(String[] args)  
    {  
    	1.ȡkey
        System.out.println(">>> " +getByKey("waltzhu"));   
    	
    	2.��key-add
    	addKey("waltzhu","123456");
    	System.out.println(">>> " +getByKey("waltzhu"));
    	
    	3.��key-set
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
    
    //1.��ȡKey
    private static String getByKey(String key){
    	 String name = mcc.get(key).toString();  
    	 return name;
    }
    
    //2.����Key-add
    private static void addKey(String key,String value){
    	mcc.add(key,value);
    }
    
    //3.����key-set
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
