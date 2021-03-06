package com.devonfw.training.hexagonal.business.ordermanagement.adapter.persistence;

import com.devonfw.training.hexagonal.business.ordermanagement.core.domain.entity.Order;
import com.devonfw.training.hexagonal.business.ordermanagement.core.domain.entity.OrderLine;
import com.devonfw.training.hexagonal.business.ordermanagement.core.domain.test.testdata.OrderTestData;
import com.devonfw.training.hexagonal.util.persistence.test.base.BasePersistenceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SaveOrderPersistenceTest extends BasePersistenceTest {

  @Autowired
  private SaveOrderPersistence saveOrderPersistence;

  @Test
  public void save() {
    // given
    Order order = OrderTestData.withoutId();

    // when
    Order createdOrder = saveOrderPersistence.save(order);

    // then
    assertOrder(order, createdOrder);
  }

  private void assertOrder(Order order, Order createdOrder) {
    assertThat(createdOrder).isNotNull();
    assertThat(createdOrder.getId()).isNotNull();
    assertThat(createdOrder.getVersion()).isNotNull();
    assertThat(createdOrder.getBookingId()).isEqualTo(order.getBookingId());
    assertThat(createdOrder.getHostName()).isEqualTo(order.getHostName());
    assertOrderLines(order.getOrderLines(), createdOrder.getOrderLines());
  }

  private void assertOrderLines(List<OrderLine> orderLines, List<OrderLine> createdOrderLines) {
    assertThat(orderLines.size()).isEqualTo(createdOrderLines.size());
    assertOrderExtraIngredient(orderLines, createdOrderLines);

    createdOrderLines.stream().
        forEach(ol -> {
          assertThat(ol.getId()).isNotNull();
          assertThat(ol.getVersion()).isNotNull();
        });
  }

  private void assertOrderExtraIngredient(List<OrderLine> orderLines, List<OrderLine> createdOrderLines) {
    long orderExtraIngredientSize = orderLines.stream().flatMap(ol -> ol.getOrderExtraIngredients().stream()).count();
    long createdOrderExtraIngredientSize = createdOrderLines.stream().flatMap(ol -> ol.getOrderExtraIngredients().stream()).count();
    assertThat(createdOrderExtraIngredientSize).isEqualTo(orderExtraIngredientSize);

    createdOrderLines.stream()
        .flatMap(ol -> ol.getOrderExtraIngredients().stream())
        .forEach(oei -> {
          assertThat(oei.getId()).isNotNull();
          assertThat(oei.getVersion()).isNotNull();
        });
  }
}