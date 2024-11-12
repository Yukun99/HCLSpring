package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderService {

  private static final CustomerRepository repository;

  static {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "com/spring/applicationContext.xml");
    repository = context.getBean("repository", CustomerRepository.class);
  }

  public void service(int id, int serviceTypeId, double amount)
      throws Exception {
    ServiceType serviceType = ServiceType.getById(serviceTypeId - 1);
    if (serviceType == null) {
      throw new Exception("Invalid service type!");
    }
    switch (serviceType) {
      case QUERY:
        System.out.println(repository.getCustomer(id).query());
        break;
      case WITHDRAW:
        System.out.println(repository.getCustomer(id).withdraw(amount));
        break;
      case DEPOSIT:
        System.out.println(repository.getCustomer(id).deposit(amount));
        break;
      case EXIT:
        System.out.println("Bye!");
    }
  }
}
