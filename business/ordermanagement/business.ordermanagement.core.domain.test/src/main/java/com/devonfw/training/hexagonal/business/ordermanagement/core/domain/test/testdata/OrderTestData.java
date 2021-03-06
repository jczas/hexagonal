package com.devonfw.training.hexagonal.business.ordermanagement.core.domain.test.testdata;

import com.devonfw.training.hexagonal.business.ordermanagement.core.domain.entity.Order;
import com.devonfw.training.hexagonal.business.ordermanagement.core.domain.entity.OrderLine;
import org.assertj.core.util.Lists;

import java.util.List;

public class OrderTestData {

  public final static Long id = 1L;

  public final static Long version = 2L;

  public final static Long bookingId = 3L;

  public final static String hostName = "Eduardo";

  public final static List<OrderLine> orderLinesWithId = Lists.list(
      OrderLineTestData.withId(6L, 1L),
      OrderLineTestData.withId(7L, 2L));

  public final static List<OrderLine> orderLinesWithoutId = Lists.list(
      OrderLineTestData.withoutId("Spaghetti"),
      OrderLineTestData.withoutId("Lasagne"));

  public static Order withoutId() {
    return withoutId(bookingId);
  }

  public static Order withoutId(Long bookingId) {
    return Order.withoutId(bookingId, hostName, orderLinesWithoutId);
  }

  public static Order withId() {
    return withId(id, version);
  }

  public static Order withId(Long id, Long version) {
    return Order.withId(id, version, bookingId, hostName, orderLinesWithId);
  }

}