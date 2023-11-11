package com.tourismagency.Model;

import com.mysql.cj.xdevapi.Client;
import com.tourismagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Reservation {
    private int id;
    private int hotel_id;
    private String roomType;
    private String clientName;
    private String clientTc;
    private String phone;
    private String email;
    private String checkIn;
    private String checkOut;
    private String days;
    private String adultNumber;
    private String childNumber;
    private String hostelTypes;
    private int totalPrice;
    private int room_id;


    private Hotel hotel;
    private Room room;




    public Reservation() {
    }

    public Reservation(int id, int hotel_id, String roomType, String clientName, String clientTc, String phone, String email, String checkIn, String checkOut,
                       String days, String adultNumber, String childNumber, String hostelTypes, int totalPrice, int room_id) {

        this.id = id;
        this.hotel_id = hotel_id;
        this.roomType = roomType;
        this.clientName = clientName;
        this.clientTc = clientTc;
        this.phone = phone;
        this.email = email;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.days = days;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
        this.hostelTypes = hostelTypes;
        this.totalPrice = totalPrice;
        this.room_id = room_id;

        this.hotel = Hotel.getFetch(hotel_id);
        this.room = Room.getFetch(room_id);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientTc() {
        return clientTc;
    }

    public void setClientTc(String clientTc) {
        this.clientTc = clientTc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(String adultNumber) {
        this.adultNumber = adultNumber;
    }

    public String getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(String childNumber) {
        this.childNumber = childNumber;
    }

    public String getHostelTypes() {
        return hostelTypes;
    }

    public void setHostelTypes(String hostelTypes) {
        this.hostelTypes = hostelTypes;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


    // reservation List
    public static ArrayList<Reservation> getList() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservations ";
        Reservation obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Reservation();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setClientName(rs.getString("client_name"));
                obj.setClientTc(rs.getString("client_tc_passport"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
                obj.setCheckIn(rs.getString("check_in"));
                obj.setCheckOut(rs.getString("check_out"));
                obj.setDays(rs.getString("days"));
                obj.setAdultNumber(rs.getString("adult_number"));
                obj.setChildNumber(rs.getString("child_number"));
                obj.setHostelTypes(rs.getString("hostel_types"));
                obj.setTotalPrice(rs.getInt("total_price"));
                obj.setRoom_id(rs.getInt("room_id"));
                reservationList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public static boolean add(int hotel_id, String roomType, String clientName, String clientTc,
                              String phone, String email, String checkIn, String checkOut, String days,
                              String adultNumber, String childNumber, String hostelTypes,
                              int totalPrice, int room_id) {
        String query = "INSERT INTO reservations (hotel_id, room_type, client_name, client_tc_passport, phone, email, check_in, check_out, days, adult_number, child_number, hostel_types, total_price,room_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, roomType);
            pr.setString(3, clientName);
            pr.setString(4, clientTc);
            pr.setString(5, phone);
            pr.setString(6, email);
            pr.setString(7, checkIn);
            pr.setString(8, checkOut);
            pr.setString(9, days);
            pr.setString(10, adultNumber);
            pr.setString(11, childNumber);
            pr.setString(12, hostelTypes);
            pr.setInt(13, totalPrice);
            pr.setInt(14, room_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM reservations WHERE id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1 , id);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
