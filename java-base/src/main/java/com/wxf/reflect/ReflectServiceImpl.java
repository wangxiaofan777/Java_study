package com.wxf.reflect;

public class ReflectServiceImpl {
	
	private String name;
	
	
	public ReflectServiceImpl() {
	}
	public ReflectServiceImpl(String name) {
		super();
		this.name = name;
	}

	public void say() {
		System.out.println("Hello " + name);
	}

	public void say(String name) {
		System.out.println("Hello " + name);
	}
}
