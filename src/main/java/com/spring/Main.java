package com.spring;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  private static final OrderService service = new OrderService();
  private static final CustomerRepository repository;

  static {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "com/spring/applicationContext.xml");
    repository = context.getBean("repository", CustomerRepository.class);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(repository);
    System.out.println("1. Query Customer\n2. Deposit Customer\n3. Withdraw Customer\n4. Exit");
    int choice = Integer.parseInt(scanner.nextLine());
    while (true) {
      int id = -1;
      double amount = -1;
      try {
        if (choice == 1) {
          System.out.println("Enter Customer ID");
          id = Integer.parseInt(scanner.nextLine());
          service.service(id, choice, amount);
        } else if (choice == 2 || choice == 3) {
          System.out.println("Enter Customer ID");
          id = Integer.parseInt(scanner.nextLine());
          System.out.println("Enter Amount");
          amount = Double.parseDouble(scanner.nextLine());
          service.service(id, choice, amount);
        } else if (choice == 4) {
          service.service(id, choice, amount);
          return;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      System.out.println(repository);
      System.out.println("1. Query Customer\n2. Deposit Customer\n3. Withdraw Customer\n4. Exit");
      choice = Integer.parseInt(scanner.nextLine());
    }
  }
}