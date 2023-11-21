package com.stackroute.slotservice.model;

public class SlotRMQ {
    private String startTime;
    private String endTime;
    private String bookingDate;
    private Boolean isBooked;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "SlotRMQ{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", isBooked=" + isBooked +
                '}';
    }
}
