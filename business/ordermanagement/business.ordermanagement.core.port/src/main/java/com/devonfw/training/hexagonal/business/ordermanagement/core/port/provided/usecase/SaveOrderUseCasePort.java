package com.devonfw.training.hexagonal.business.ordermanagement.core.port.provided.usecase;

import com.devonfw.training.hexagonal.business.ordermanagement.core.domain.entity.Order;

public interface SaveOrderUseCasePort {

  Order save(Order order);

}
