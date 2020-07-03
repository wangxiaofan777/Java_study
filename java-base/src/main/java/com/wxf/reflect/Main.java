package com.wxf.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Main {
	
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		System.out.println(sdf.format(new Date()));
		System.out.println((int)(Math.random()*9+1)*100000);
//		getInstance();
	}
	
	public static ReflectServiceImpl getInstance() {
		ReflectServiceImpl object = null;  
		try {
			//构建无参对象
			object = (ReflectServiceImpl) Class.forName("com.wxf.reflect.ReflectServiceImpl").newInstance();
			//构建有参对象
			object = (ReflectServiceImpl) Class.forName("com.wxf.reflect.ReflectServiceImpl").getConstructor(String.class).newInstance("王小凡");
			
			Method method = object.getClass().getMethod("say", String.class);
			method.invoke(object, "我是你爹");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return object;
	}
}
