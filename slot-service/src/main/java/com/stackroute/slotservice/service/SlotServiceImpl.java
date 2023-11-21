package com.stackroute.slotservice.service;

import com.stackroute.slotservice.model.Slot;
import com.stackroute.slotservice.repo.SlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlotServiceImpl implements SlotService {

    private SlotRepo slotRepo;

    @Autowired
    public SlotServiceImpl(SlotRepo slotRepo){

        this.slotRepo =slotRepo;
    }
    @Override
    public List<Slot> getAllSlot() {
        return slotRepo.findAll();
    }

    @Override
    public Slot addSlot(Slot slot) {
        return slotRepo.save(slot);
    }

    @Override
    public boolean updateSlot(String productId, Slot slot) {
//        Slot newSlot= slotRepo.getByProductId(productId,slot);
//        newSlot.setBooked(true);
//        newSlot.setBuyerId(slot.getBuyerId());
        List<Slot> slots=slotRepo.getSlotsByProductId(productId);
//        System.out.println(slots.toString());
//        Slot foundSlot;

        for (Slot value : slots) {
            if (value.getProductId().equals(slot.getProductId()) &&
                    value.getEndTime().equals(slot.getEndTime()) &&
                    value.getStartTime().equals(slot.getStartTime())
            ) {
//                System.out.println(value.toString());
                value.setBuyerId(slot.getBuyerId());
                value.setBooked(true);
                slotRepo.save(value);
                return true;
            }
        }

return false;
//        System.out.println(slot.toString());
    }

    @Override
    public void deleteSlot(String slotId) {
        slotRepo.deleteById(slotId);
    }

    @Override
    public Optional<Slot> getSlotById(String id) {
        return slotRepo.findById(id);
    }
}
