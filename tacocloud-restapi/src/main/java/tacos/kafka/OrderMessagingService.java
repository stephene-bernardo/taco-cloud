package tacos.kafka;

import tacos.domain.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
