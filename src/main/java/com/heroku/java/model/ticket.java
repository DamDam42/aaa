package com.heroku.java.model;

public class ticket {
    private String tickettype;
    private double ticketprice;
    private int ticketid;

    public ticket(){
        
    }

    public String getTicketType() {
        return tickettype;
    }

    public void setTicketType(String tickettype) {
        this.tickettype = tickettype;
    }

    public double getTicketPrice() {
        return ticketprice;
    }

    public void setTicketPrice(double ticketprice) {
        this.ticketprice = ticketprice;
    }

    public int getTicketId() {
        return ticketid;
    }

    public void setTicketType(int ticketid) {
        this.ticketid = ticketid;
    }
}
