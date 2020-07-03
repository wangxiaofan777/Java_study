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
			//�����޲ζ���
			object = (ReflectServiceImpl) Class.forName("com.wxf.reflect.ReflectServiceImpl").newInstance();
			//�����вζ���
			object = (ReflectServiceImpl) Class.forName("com.wxf.reflect.ReflectServiceImpl").getConstructor(String.class).newInstance("��С��");
			
			Method method = object.getClass().getMethod("say", String.class);
			method.invoke(object, "�������");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return object;
	}
}
