package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByZip(String deliveryZip);

    List<Order> findOrdersByZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
