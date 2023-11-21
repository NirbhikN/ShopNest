package com.stackroute.slotservice.config;


import com.stackroute.slotservice.model.ProductRMQ;
import com.stackroute.slotservice.model.Slot;
import com.stackroute.slotservice.model.SlotRMQ;
import com.stackroute.slotservice.service.SlotServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {

    @Autowired
    private SlotServiceImpl slotService;

    @RabbitListener(queues = "product_queue")
    public void getUserRMQFromRabbitMq(ProductRMQ productRMQ) {
        List<SlotRMQ> allSlots = productRMQ.getSlots();

        if (allSlots != null && !allSlots.isEmpty()) {
            for (SlotRMQ allSlot : allSlots) {
                Slot sl = new Slot();
                sl.setProductId(productRMQ.getProductId());
                sl.setSellerId(productRMQ.getSellerId());
                sl.setStartTime(allSlot.getStartTime());
                sl.setEndTime(allSlot.getEndTime());
                sl.setBookingDate(allSlot.getBookingDate());
                sl.setBooked(allSlot.getBooked());
                slotService.addSlot(sl);
            }
        }
    }
}
