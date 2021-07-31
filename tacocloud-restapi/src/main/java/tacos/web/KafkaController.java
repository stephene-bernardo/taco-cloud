package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.domain.Order;
import tacos.kafka.OrderMessagingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/kafka")
public class KafkaController {
    private OrderMessagingService orderMessagingService;


    @Autowired
    public KafkaController(OrderMessagingService orderMessagingService) {
        System.out.println("-----------1");
        this.orderMessagingService = orderMessagingService;
    }

    @GetMapping("/placeorder")
    public String placeOrder(){
        System.out.println("-----------");
        Order order = new Order();
        order.setName("just naming it to send");
        orderMessagingService.sendOrder(order);
        return "";
    }

    @GetMapping()
    public String log(){
        Order order = new Order();
        order.setName("just naming it to send");
        orderMessagingService.sendOrder(order);
        return "";

    }
}
