package com.fishroad.util;

import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;  
/* 
 项目中http通信离不开对象的序列化和反序列化，以前框架使用的是xml，通用、可读性强， 
对于对速度要求不高的系统来说，的确是一种不错的选择。然而最近的一个需求需要使用protobuf，因为其速度比xml快非常多， 
而业界说到java的序列化和反序列化，更离不开基于protobuf的protostuff 
*/  
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProtostuffUtil {  
      
	public static List<byte[]> serializeProtoStuffObjectList(List list,Class clazz) {  
        if(list == null  ||  list.size() <= 0) {  
            return null;  
        }  
   
        long start = System.currentTimeMillis() ;  
        List<byte[]> bytes = new ArrayList<byte[]>();  
		Schema  schema = RuntimeSchema.getSchema(clazz);  
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);  
        byte[] protostuff = null;  
        for(Object p : list) {  
            try {  
                protostuff = ProtostuffIOUtil.toByteArray(p, schema, buffer);  
                bytes.add(protostuff);  
            } finally {  
                buffer.clear();  
            }  
        }  
        long end = System.currentTimeMillis() ;  
        System.out.println("usetime is"+(end - start));  
        return bytes;  
    }  
      
     
	public static List  deserializeProtoStuffDataListToObjectList(  
                List<byte[]> bytesList,Class clazz) {  
            if(bytesList == null || bytesList.size() <= 0) {  
                return null;  
            }  
            long start = System.currentTimeMillis() ;  
            Schema  schema = RuntimeSchema.getSchema(clazz);  
            List<Object> list = new ArrayList<Object>();  
            for(byte[] bs : bytesList) {  
                Object obj = null;  
                try {  
                    obj = clazz.newInstance();  
                } catch (InstantiationException e) {  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    e.printStackTrace();  
                }  
                ProtostuffIOUtil.mergeFrom(bs,obj, schema);  
                list.add(obj);  
            }  
            long end = System.currentTimeMillis() ;  
            System.out.println("usetime is"+(end - start));  
            return list;  
        }  
      
      
        public static  byte[]  serializeProtoStuffObject(Object obj,Class clazz) {  
            if(obj == null) {  
                return null;  
            }  
            long start = System.currentTimeMillis() ;  
            Schema  schema = RuntimeSchema.getSchema(clazz);  
            LinkedBuffer buffer = LinkedBuffer.allocate(4096);  
            byte[] protostuff = null;  
            protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);  
            long end = System.currentTimeMillis() ;  
            System.out.println("usetime is"+(end - start));  
            return protostuff;  
        }  
          
          
         public static Object  deserializeProtoStuffDataListToProductsObject(  
                     byte[]  bytes,Class clazz) {  
             if(bytes == null) {  
                    return null;  
                }  
                long start = System.currentTimeMillis() ;  
                Schema  schema = RuntimeSchema.getSchema(clazz);  
      
                Object obj = null;  
                try {  
                    obj = clazz.newInstance();  
                } catch (InstantiationException e) {  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    e.printStackTrace();  
                }  
              
                ProtostuffIOUtil.mergeFrom(bytes,obj, schema);  
                long end = System.currentTimeMillis() ;  
                System.out.println("usetime is"+(end - start));  
                return obj;  
            }  
  
}  