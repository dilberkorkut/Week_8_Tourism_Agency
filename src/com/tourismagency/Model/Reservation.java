package com.tourismagency.Model;

import com.mysql.cj.xdevapi.Client;

public class Reservation {
    private int id;
    private int clientName;
    private int clientTc;
    private String phone;
    private String email;
    private String checkIn;
    private String checkOut;
    private String days;
    private String adultNumber;
    private String childNumber;
    private String totalPrice;

    private Hotel hotel;
    private int hotel_id;

    private Room room;
    private int room_id;

    private Price price;
    private int price_id;

    public Reservation() {
    }

    public Reservation(int id, int clientName, int clientTc, String phone, String email, String checkIn, String checkOut, String days, String adultNumber, String childNumber, String totalPrice, int hotel_id, int room_id, int price_id) {
        this.id = id;
        this.clientName = clientName;
        this.clientTc = clientTc;
        this.phone = phone;
        this.email = email;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.days = days;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
        this.totalPrice = totalPrice;
        this.hotel_id = hotel_id;
        this.room_id = room_id;
        this.price_id = price_id;

        this.hotel = Hotel.getFetch(hotel_id);
        this.room = Room.getFetch(room_id);
        // this.price = Price.

    }
}
