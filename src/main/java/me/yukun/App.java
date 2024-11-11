package me.yukun;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("student.xml");
		Student student1 = (Student) context.getBean("student");
		System.out.println(student1);
	}
}
