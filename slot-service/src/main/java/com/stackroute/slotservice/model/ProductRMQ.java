package com.stackroute.slotservice.model;

import java.util.List;

public class ProductRMQ {
    public String productId;
    private String sellerId;

    private String status;

    private List<SlotRMQ> slots;

    public List<SlotRMQ> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotRMQ> slots) {
        this.slots = slots;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSellerId() {
        return sellerId;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public ProductRMQ() {
    }

    @Override
    public String toString() {
        return "ProductRMQ{" +
                "productId='" + productId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", status='" + status + '\'' +
                ", slotRMQ=" + slots +
                '}';
    }
}

