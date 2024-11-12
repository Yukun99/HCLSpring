package com.spring;

import java.util.HashMap;
import java.util.Map;

public enum ServiceType {
  QUERY(0),
  DEPOSIT(1),
  WITHDRAW(2),
  EXIT(3);

  private final int id;
  private static final Map<Integer, ServiceType> idServiceTypeMap = new HashMap<>();

  static {
    for (ServiceType serviceType : ServiceType.values()) {
      idServiceTypeMap.put(serviceType.id, serviceType);
    }
  }

  ServiceType(int id) {
    this.id = id;
  }

  public static ServiceType getById(int id) {
    return idServiceTypeMap.get(id);
  }
}
