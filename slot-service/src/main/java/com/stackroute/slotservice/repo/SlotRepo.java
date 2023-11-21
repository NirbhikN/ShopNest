package com.stackroute.slotservice.repo;


import com.stackroute.slotservice.model.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepo extends MongoRepository<Slot,String>{
    List<Slot> getSlotsByProductId(String productId);



}