package com.heroku.java.model;

public class ticket {
    private String tickettype;
    private double ticketprice;
    private int ticketid;

    public ticket(){
        
    }

    public String getTicketType() {
        return this.tickettype;
    }

    public void setTicketType(String ticket) {
        this.tickettype = ticket;
    }

    public double getTicketPrice() {
        return this.ticketprice;
    }

    public void setTicketPrice(double ticketprice) {
        this.ticketprice = ticketprice;
    }

    public int getTicketId() {
        return this.ticketid;
    }

    public void setTicketType(int ticketid) {
        this.ticketid = ticketid;
    }
}
