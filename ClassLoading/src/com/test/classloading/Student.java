package com.test.classloading;

public class Student {

	public static final String univName ="IIT";
	public static String test ="test";
	
	private int id;
	private String name;
	
	static{
		System.out.println("static block");
	}
	public Student() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public static void testMethod(){
		while (true) {
			if (test.equalsIgnoreCase("exit")) {
				//queueCon.close();
				System.out.println("QueueConsumer exit");
				System.exit(0);// exit program
			}else{
				//queueCon.start();
				System.out.println("system needs input");
				test = null;
			}
		}
			
	}
	
}
