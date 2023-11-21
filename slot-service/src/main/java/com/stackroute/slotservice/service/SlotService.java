package com.stackroute.slotservice.service;

import com.stackroute.slotservice.model.Slot;

import java.util.List;
import java.util.Optional;

public interface SlotService {
    public List<Slot> getAllSlot();

    public Slot addSlot(Slot slot);

    public boolean updateSlot(String id, Slot slot);

    public void deleteSlot(String slotId);

    public Optional<Slot> getSlotById(String id);
}
