package com.stackroute.slotservice.controller;

import com.stackroute.slotservice.model.Slot;
import com.stackroute.slotservice.repo.SlotRepo;
import com.stackroute.slotservice.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("slots")
public class SlotController {
    @Autowired
    private SlotService slotService;

    @Autowired
    private SlotRepo slotRepo;


    @GetMapping("/all")
    public List<Slot> getAllSlots() {
        return slotService.getAllSlot();
    }

    @PostMapping("/add")
    public Slot addSlot(@RequestBody Slot slot) {
        return slotService.addSlot(slot);
    }

    @PutMapping("/edit/{id}")
    public boolean updateSlot(@PathVariable String id,@RequestBody Slot slot){
        return slotService.updateSlot(id,slot);

    }

    @GetMapping("/product/{id}")
    Optional<Slot> getProduct(@PathVariable String id){
        return slotService.getSlotById(id);
    }

    @DeleteMapping("/delete/{slotId}")
    public void deleteSlot(@PathVariable String slotId) {
        slotService.deleteSlot(slotId);
    }

    @GetMapping("/productId/{productId}")
    public List<Slot> getSlotsByProductId(@PathVariable String productId){
        List<Slot> slots=slotRepo.getSlotsByProductId(productId);
        return slots;
    }
}




