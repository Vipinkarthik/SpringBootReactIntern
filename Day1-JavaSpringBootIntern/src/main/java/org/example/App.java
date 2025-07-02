package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("resources.xml");
        student s = (student) context.getBean("st");
        s.show();
        s.internshipProgram();
        System.out.println("Hello World!");
    }
}
