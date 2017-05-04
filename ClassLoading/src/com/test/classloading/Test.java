package com.test.classloading;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("going to check class loading of JVM");
		@SuppressWarnings("unused")
		Student studObj;
		Thread.sleep(4000);
		System.out.println("going to create stud obj");
		studObj = new Student();
	}

}
