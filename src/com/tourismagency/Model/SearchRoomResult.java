package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchRoomResult {

//    private String startDate;
//    private String endDate;
    private String season;
    private int room_id;
    private int hotel_id;
    private String hotelName;
    private String city;
    private String region;
    private String address;
    private String email;
    private String phone;
    private String starRate;
    private String facilityFeatures = "";
    private String hostelType = "";

    private String roomType;
    private String stock;
    private String beds;
    private String tv;
    private String minibar;


    private Hotel hotel;
    private Room room;


    public SearchRoomResult(String season, int hotel_id, int room_id, String hotelName, String city, String region, String address, String email, String phone,
                            String starRate, String facilityFeatures, String hostelType, String roomType, String stock, String beds, String tv, String minibar) {

        this.season = season;
        this.hotel_id = hotel_id;
        this.room_id = room_id;
        this.hotelName = hotelName;
        this.city = city;
        this.region = region;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.starRate = starRate;
        this.facilityFeatures = facilityFeatures;
        this.hostelType = hostelType;
        this.roomType = roomType;
        this.stock = stock;
        this.beds = beds;
        this.tv = tv;
        this.minibar = minibar;

        this.hotel = Hotel.getFetch(hotel_id);
        this.room = Room.getFetch(room_id);
    }

    public SearchRoomResult() {
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    public String getFacilityFeatures() {
        return facilityFeatures;
    }

    public void setFacilityFeatures(String facilityFeatures) {
        this.facilityFeatures = facilityFeatures;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getMinibar() {
        return minibar;
    }

    public void setMinibar(String minibar) {
        this.minibar = minibar;
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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }


    public static ArrayList<SearchRoomResult> getSearchRoomResult(String query) { // Veritabanindan SearchRoomResult objelerini getiren metot
        ArrayList<SearchRoomResult> searchRoomResultList = new ArrayList<>();
        SearchRoomResult obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new SearchRoomResult();

                obj.setSeason(rs.getString("season"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoom_id(rs.getInt("id"));
                obj.setHotelName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStarRate(rs.getString("star_rate"));
                obj.setFacilityFeatures(rs.getString("facility_features"));
                obj.setHostelType(rs.getString("hostel_types"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setStock(rs.getString("stock"));
                obj.setBeds(rs.getString("beds"));
                obj.setTv(rs.getString("tv"));
                obj.setMinibar(rs.getString("minibar"));
                searchRoomResultList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return searchRoomResultList;
    }

    public static String searchRoomQuery(String city, String region, String startDate, String endDate) { // Otel arama sorgusu olusturan metot

//        String query = "SELECT * FROM hotel_seasons INNER JOIN hotels ON hotel_seasons.hotel_id = hotels.id INNER JOIN rooms ON hotels.id = rooms.hotel_id";
//        query += "\nWHERE hotels.city LIKE '%{{city}}%'";
//        query = query.replace("{{city}}", city);

        String query = "SELECT * FROM rooms INNER JOIN hotels ON rooms.hotel_id = hotels.id INNER JOIN hotel_seasons ON hotels.id = hotel_seasons.hotel_id";
        query += "\nWHERE hotels.city LIKE '%{{city}}%'"; //Sehir filtresi LIKE kullanarak sehir kayitlarini getirir. {city} ifadesi, kullanicinin girdigi şehir adı ile değiştirilir.
        query = query.replace("{{city}}", city);

        query += " AND hotels.region LIKE '%{{region}}%'";
        query = query.replace("{{region}}", region);

        if (!startDate.isEmpty() && (!endDate.isEmpty())) {

            query += "\n AND STR_TO_DATE(start_date,'%d/%m/%Y') <= STR_TO_DATE('{{startDate}}','%d/%m/%Y') AND STR_TO_DATE(end_date,'%d/%m/%Y') >= STR_TO_DATE('{{endDate}}','%d/%m/%Y')";
            query = query.replace("{{startDate}}", startDate);
            query = query.replace("{{endDate}}", endDate);

            // STR_TO_DATE fonksiyonu kullanılarak tarih formatı uygun hale getirilir.
        }

        return query;
    }

}



