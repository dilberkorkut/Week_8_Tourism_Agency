package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Price {
    private int id;
    private int hotel_id;
    private int room_id;
    private int season_id;
    private String startDate;
    private String endDate;
    private String season;
    private int hostel_id;
    private String hostelType;
    private String roomType;
    private String age;
    private String price;

    private Hotel hotel;
    private String hotelName;
    private Hostel hostel;
    private HotelSeason hotelSeason;
    private Room room;

    public Price(int id, int hotel_id, int room_id, int season_id, int hostel_id, String age, String price) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.room_id = room_id;
        this.season_id = season_id;
        this.season = HotelSeason.getFetch(season_id).getSeason();
        this.hostel_id = hostel_id;
        this.age = age;
        this.price = price;

        this.room = Room.getFetch(room_id);

        this.hotel = Hotel.getFetch(hotel_id);
        this.hotelName = Hotel.getFetch(hotel_id).getName();
        this.hotelSeason = HotelSeason.getFetch(season_id);
        this.hostel = Hostel.getFetch(hostel_id);

        this.startDate = HotelSeason.getFetch(season_id).getStartDate();
        this.endDate = HotelSeason.getFetch(season_id).getEndDate();
        this.hostelType = Hostel.getFetch(hostel_id).getHostelType();
        this.roomType = Room.getFetch(room_id).getRoomType();
    }

    public Price() {

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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getHostel_id() {
        return hostel_id;
    }

    public void setHostel_id(int hostel_id) {
        this.hostel_id = hostel_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public HotelSeason getHotelSeason() {
        return hotelSeason;
    }

    public void setHotelSeason(HotelSeason hotelSeason) {
        this.hotelSeason = hotelSeason;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getHostelType() {
        return hostelType;
    }

    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public static ArrayList<Price> getList() {
        ArrayList<Price> priceList = new ArrayList<>();
        String query = "SELECT * FROM prices ";
        Price obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Price();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoom_id(rs.getInt("room_id"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setStartDate(rs.getString("start_date"));
                obj.setEndDate(rs.getString("end_date"));
                obj.setSeason(rs.getString("season"));
                obj.setHostel_id(rs.getInt("hostel_id"));
                obj.setHostelType(rs.getString("hostel_types"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setAge(rs.getString("age"));
                obj.setPrice(rs.getString("price"));
                priceList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceList;
    }

    public static ArrayList<Price> getList(int id) {
        ArrayList<Price> priceList = new ArrayList<>();
        String query = "SELECT * FROM prices ";
        Price obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Price();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoom_id(rs.getInt("room_id"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setStartDate(rs.getString("start_date"));
                obj.setEndDate(rs.getString("end_date"));
                obj.setSeason(rs.getString("season"));
                obj.setHostel_id(rs.getInt("hostel_id"));
                obj.setHostelType(rs.getString("hostel_types"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setAge(rs.getString("age"));
                obj.setPrice(rs.getString("price"));
                priceList.add(obj);
            }
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceList;
    }
    public static boolean add(int hotel_id, int season_id, int room_id, String startDate, String endDate, String season, int hostel_id, String hostelType, String roomType, String age, String price) {
        String query = "INSERT INTO prices (hotel_id, season_id, room_id, start_date, end_date, season, hostel_id, hostel_types, room_type, age, price) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setInt(2, season_id);
            pr.setInt(3, room_id);
            pr.setString(4, startDate);
            pr.setString(5, endDate);
            pr.setString(6, season);
            pr.setInt(7, hostel_id);
            pr.setString(8, hostelType);
            pr.setString(9, roomType);
            pr.setString(10, age);
            pr.setString(11, price);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM prices WHERE id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1 , id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }


    public static Price getFetch(int id){
        Price obj = null;
        String query = "SELECT * FROM rooms WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Price();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoom_id(rs.getInt("room_id"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setStartDate(rs.getString("start_date"));
                obj.setEndDate(rs.getString("end_date"));
                obj.setSeason(rs.getString("season"));
                obj.setHostel_id(rs.getInt("hostel_id"));
                obj.setHostelType(rs.getString("hostel_types"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setAge(rs.getString("age"));
                obj.setPrice(rs.getString("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
