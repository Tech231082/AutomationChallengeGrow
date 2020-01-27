package com.qa.testCases;

import org.testng.TestNG;


//creating a jar file
public class TestRunner {
	static TestNG testng;   //reference to TestNG class 

	public static void main(String[] args) {
		testng=new TestNG();
		testng.setTestClasses(new Class[] {HomePageTest.class});
		testng.run();

	}

}
